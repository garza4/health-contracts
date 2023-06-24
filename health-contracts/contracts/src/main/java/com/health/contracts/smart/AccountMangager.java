
package com.health.contracts.smart;

import com.health.contracts.entity.FundReceiptEntity;
import com.health.contracts.model.BalanceList;
import com.health.contracts.model.PaymentDecisions;
import com.health.contracts.repository.ReceiptRepository;
import com.health.contracts.service.impl.AccountImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.stellar.sdk.responses.AccountResponse.Balance;

/**
 * This class will help manage the funds in the master account.
 * @author Bobby
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class AccountMangager {
    private final AccountImpl accountImpl;
    private final ReceiptRepository receiptRepository;
    private void manageMasterAccount(){
        try{
            BalanceList balance = accountImpl.checkBalance("750000000");
            List<FundReceiptEntity> receipts = receiptRepository.getPendingReceipts();
            String accountBalance = balance.getBalances().get(0).getBalance();
            PaymentDecisions decisions = optimizePayments(receipts,accountBalance);
            decisions.getCanPay().forEach(entity->entity.setStatus("R"));
            receiptRepository.saveAll(decisions.getCanPay());
        }catch(Exception e){
            log.error("error managing account",e);
        }
        
        
    }
    /**
     * prioritize higher payments and separate into lists of can pay and cant pay
     * @param receipts
     * @param accountBalance 
     */
    private PaymentDecisions optimizePayments(List<FundReceiptEntity> receipts, String accountBalance){
        PaymentDecisions paymentDecisions = new PaymentDecisions(new ArrayList<FundReceiptEntity>(),new ArrayList<FundReceiptEntity>());

        try{
            String totalOfPendingRequests = getAllPendingRequestAmounts(receipts);
            //sort based on amount
            receipts.sort(Comparator.comparing(FundReceiptEntity::getTransferAmount));
            if(Integer.valueOf(totalOfPendingRequests) < Integer.valueOf(accountBalance)){
                paymentDecisions.getCanPay().addAll(receipts);
            }else{
                int sum = 0;
                var toPay = new ArrayList<FundReceiptEntity>();
                var cantPay = new ArrayList<FundReceiptEntity>();
                int endOfReceipts = receipts.size()-1;
                while(sum < Integer.valueOf(accountBalance)){
                    if(endOfReceipts ==0){
                        sum += Integer.valueOf(receipts.get(endOfReceipts).getTransferAmount());
                        if(!sumGreaterThanBalance(sum,accountBalance)){
                            toPay.add(receipts.get(endOfReceipts));
                        }else{
                            sum -= Integer.valueOf(receipts.get(endOfReceipts).getTransferAmount());
                            cantPay.add(receipts.get(endOfReceipts));
                            //will be empty list here
                            receipts.remove(endOfReceipts);
                        }
                        break;
                    }
                    sum += Integer.valueOf(receipts.get(endOfReceipts).getTransferAmount());
                    toPay.add(receipts.get(endOfReceipts));
                    if(sumGreaterThanBalance(sum,accountBalance)){
                        sum -= Integer.valueOf(receipts.get(endOfReceipts).getTransferAmount());
                        toPay.remove(toPay.size()-1);
                        cantPay.add(receipts.get(endOfReceipts));
                        //cant pay values are in cant pay list
                        receipts.remove(endOfReceipts);
                        endOfReceipts = receipts.size()-1;
                    }
                    endOfReceipts--;
                }
            }
            
        }catch(Exception e){
            log.error("could not optimize",e);
        }
        return paymentDecisions;
    }
    
    private Boolean sumGreaterThanBalance(int sum, String accountBalance){
        return sum > Integer.valueOf(accountBalance);
    }
    
    private String getAllPendingRequestAmounts(List<FundReceiptEntity> receipts){
        Integer sum =  receipts.stream()
                .map(x->Integer.valueOf(x.getTransferAmount()))
                .reduce(0, (a, b) -> a + b);
        return new String().valueOf(sum);
    }
    
}
