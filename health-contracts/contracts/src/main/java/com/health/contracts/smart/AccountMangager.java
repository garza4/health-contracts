
package com.health.contracts.smart;

import com.health.contracts.entity.FundReceiptEntity;
import com.health.contracts.model.BalanceList;
import com.health.contracts.model.PaymentDecisions;
import com.health.contracts.repository.ReceiptRepository;
import com.health.contracts.service.impl.AccountImpl;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
    @Value("${stellar.master.acct}") private final String account;
    public PaymentDecisions manageMasterAccount(){
        var finalDecision = new PaymentDecisions(new ArrayList<>(),new ArrayList<>());
        try{
            BalanceList balance = accountImpl.checkBalance(account);
            List<FundReceiptEntity> receipts = receiptRepository.getPendingReceipts();
            String accountBalance = balance.getBalances().get(0).getBalance();
            
            //payments that can be made right off the bat...
            var initialDecisions = optimizePayments(receipts,accountBalance);
            //best payments from cant pay list
            var secondaryDecisions = optimizePayments(initialDecisions.getCantPay(),accountBalance);
            finalDecision.getCanPay().addAll(secondaryDecisions.getCanPay());
            finalDecision.getCanPay().addAll(initialDecisions.getCanPay());
            finalDecision = optimizePayments(finalDecision.getCanPay(),accountBalance);
            
            finalDecision.getCanPay().forEach(entity->entity.setStatus("R"));
            receiptRepository.saveAll(finalDecision.getCanPay());
        }catch(Exception e){
            log.error("error managing account",e);
        } 
        return finalDecision;
    }
    /**
     * prioritize higher payments and separate into lists of can pay and cant pay
     * @param receipts
     * @param accountBalance 
     */
    private PaymentDecisions optimizePayments(List<FundReceiptEntity> receipts, String accountBalance){
        var paymentDecisions = new PaymentDecisions(new ArrayList<>(),new ArrayList<>());
        var toPay = new ArrayList<FundReceiptEntity>();
        var cantPay = new ArrayList<FundReceiptEntity>();
        var copyOfReceipts = new ArrayList<FundReceiptEntity>(receipts);
        try{
            String totalOfPendingRequests = getAllPendingRequestAmounts(copyOfReceipts);
            //sort based on amount
            copyOfReceipts.sort(Comparator.comparing(FundReceiptEntity::getTransferAmount));
            if(Integer.valueOf(totalOfPendingRequests) < Integer.valueOf(accountBalance)){
                paymentDecisions.getCanPay().addAll(copyOfReceipts);
            }else{
                int sum = 0;
                
                int endOfReceipts = copyOfReceipts.size()-1;
                while(sum <= Integer.parseInt(accountBalance)){
                    if(copyOfReceipts.size() == 0) break;
                    if(copyOfReceipts.size()== 1){
                        sum += Integer.valueOf(copyOfReceipts.get(endOfReceipts).getTransferAmount());
                        if(!sumGreaterThanBalance(sum,accountBalance)){
                            toPay.add(copyOfReceipts.get(endOfReceipts));
                        }else{
                            sum -= Integer.valueOf(copyOfReceipts.get(endOfReceipts).getTransferAmount());
                            cantPay.add(copyOfReceipts.get(endOfReceipts));
                            //will be empty list here
                            copyOfReceipts.remove(endOfReceipts);
                        }
                        break;
                    }
                    sum += Integer.valueOf(copyOfReceipts.get(endOfReceipts).getTransferAmount());
                    toPay.add(copyOfReceipts.get(endOfReceipts));
                    if(sumGreaterThanBalance(sum,accountBalance)){
                        sum -= Integer.valueOf(copyOfReceipts.get(endOfReceipts).getTransferAmount());
                        toPay.remove(toPay.size()-1);
                        cantPay.add(copyOfReceipts.get(endOfReceipts));
                    }
                    //remove assesed receipt from copy list
                    copyOfReceipts.remove(endOfReceipts);
                    endOfReceipts = copyOfReceipts.size()-1;
                    endOfReceipts--;
                }
            }
            //after assesement add all to respective lists
            paymentDecisions.getCanPay().addAll(toPay);
            paymentDecisions.getCantPay().addAll(cantPay);
            paymentDecisions.getCantPay().addAll(copyOfReceipts);
            
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
