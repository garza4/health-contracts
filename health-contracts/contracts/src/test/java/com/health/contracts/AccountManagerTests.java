package com.health.contracts;

import com.health.contracts.repository.ReceiptRepository;
import com.health.contracts.smart.AccountMangager;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.health.contracts.TestUtil;
import com.health.contracts.model.PaymentDecisions;

/**
 *
 * @author Bobby
 */

public class AccountManagerTests {
    @MockBean
    @Autowired
    AccountMangager accountManager;
    
    @MockBean
    @Autowired
    ReceiptRepository receiptRepository;
    
    @MockBean
    @Autowired
    TestUtil testUtil;
    
//    @Test
//    public void testOptimization(){
//      when(receiptRepository.getPendingReceipts()).thenReturn(testUtil.getReceipts());
//      try{
//          PaymentDecisions decision = accountManager.manageMasterAccount();
//      }catch(Exception e){
//          System.out.println("print out error: " + e);
//      }
//      
//    }
    
}
