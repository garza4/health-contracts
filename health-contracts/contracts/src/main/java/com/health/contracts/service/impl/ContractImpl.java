package com.health.contracts.service.impl;

import com.health.contracts.entity.FundReceiptEntity;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Component;
import org.stellar.sdk.AssetTypeNative;
import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Network;
import org.stellar.sdk.PaymentOperation;
import org.stellar.sdk.Server;
import org.stellar.sdk.Transaction;
import org.stellar.sdk.TransactionBuilderAccount;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.SubmitTransactionResponse;

import lombok.extern.slf4j.Slf4j;

import com.health.contracts.model.FundReq;
import com.health.contracts.model.ReqFundsResp;
import com.health.contracts.repository.ReceiptRepository;
import java.sql.Timestamp;
//import com.health.contracts.security.IAuthenticationFacade;

@Component
@Slf4j
public class ContractImpl implements ContractApi {
	private String stellarServer = "https://horizon-testnet.stellar.org";
	private AccountImpl accountImpl;
//	private IAuthenticationFacade userContext;
        private ReceiptRepository receiptRepo;
	@Autowired
	public ContractImpl(AccountImpl accountImpl,ReceiptRepository receiptRepo) {
		this.accountImpl = accountImpl;
//		this.userContext = userContext;
                this.receiptRepo=receiptRepo;
	}

	Server server = new Server(stellarServer);

	@Override
	public ReqFundsResp requestFunds(FundReq req) {
		SubmitTransactionResponse response = null;
		ReqFundsResp transfer = new ReqFundsResp();
		try {
			log.info("Transferring {} funds to user", req.getAmount());
			server.accounts().account(req.getId());

			if (Double.valueOf(accountImpl.checkBalance(req.getPublicMasterAccount()).getBalances().get(0).getBalance()) < Double.valueOf(req.getAmount())) {
				log.error("could not transfer funds at this moment due to lack of funds");
				throw new Exception("Not enough funds for this request, try again later");
			}
			AccountResponse sourceAccount = server.accounts().account(req.getPublicMasterAccount());
			Transaction transaction = createTransaction(sourceAccount, req.getId(), req.getAmount(),req.getSourceSecret());
			response = server.submitTransaction(transaction);
			log.info("Successfull transfer of funds {}", response.getHash());
			transfer.setAmount(req.getAmount());
			transfer.setDestination(req.getId());
			transfer.setFrom(req.getPublicMasterAccount());
                        receiptRepo.save(new FundReceiptEntity(null,new Timestamp(System.currentTimeMillis()),req.getAmount(),req.getAdminUser(),req.getPatient(),"P"));
                        
		} catch (IOException io) {
			log.error("stellar returned io error", io);
		} catch (Exception e) {
			log.error("Could not transfer funds in the amount of {} ", req.getAmount(), e);
		}
		return transfer;
	}

	/**
	 * send funds from source to destination
	 * 
	 * @param source
	 * @param destination
	 * @param amount
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private Transaction createTransaction(TransactionBuilderAccount source, String destination, String amount,String secret) {
		Transaction transaction = null;
		try {
			transaction = new Transaction.Builder(source, Network.TESTNET)
					.addOperation(new PaymentOperation.Builder(destination, new AssetTypeNative(), amount).build())
			        .setTimeout(180)
					.setBaseFee(Transaction.MIN_BASE_FEE).build();
			// Sign the transaction to prove you are actually the person sending it.
			transaction.sign(KeyPair.fromSecretSeed(secret));
		} catch (Exception e) {
			log.error("could not create transaction", e);
		}
		return transaction;
	}

}
