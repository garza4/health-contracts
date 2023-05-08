package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.stellar.sdk.AssetTypeNative;
import org.stellar.sdk.Network;
import org.stellar.sdk.PaymentOperation;
import org.stellar.sdk.Server;
import org.stellar.sdk.Transaction;
import org.stellar.sdk.TransactionBuilderAccount;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.SubmitTransactionResponse;
import org.stellar.sdk.xdr.DecoratedSignature;

import lombok.extern.slf4j.Slf4j;
import model.FundReq;

@Component
@Slf4j
public class ContractImpl implements ContractApi{
	@Value("${stellar.net}")	
	private String stellarServer;
	
	private String sourceId;
	Server server = new Server(stellarServer);
	@Override
	public Boolean requestFunds(FundReq req) {
		SubmitTransactionResponse response = null;
		Boolean successTransfer = false;
		try {
			log.info("Transferring {} funds to user",req.getAmount());
			server.accounts().account(req.getId());
			AccountResponse sourceAccount = server.accounts().account(sourceId);
			Transaction transaction = createTransaction(sourceAccount,req.getId(),req.getAmount());
			response = server.submitTransaction(transaction);
			log.info("Successfull transfer of funds {}", response.getHash());
			successTransfer=true;
		}catch(Exception e) {
			log.error("Could not transfer funds in the amount of {} ", req.getAmount());
		}
		return successTransfer;
	}
	
	private Transaction createTransaction(TransactionBuilderAccount source, String destination, String amount) {
		Transaction transaction = new Transaction.Builder(source, Network.TESTNET)
		        .addOperation(new PaymentOperation.Builder(destination, new AssetTypeNative(), amount).build())
		        .setBaseFee(Transaction.MIN_BASE_FEE)
		        .build();
		// Sign the transaction to prove you are actually the person sending it.
		transaction.addSignature((DecoratedSignature) source);
		return transaction;
	}
	
}
