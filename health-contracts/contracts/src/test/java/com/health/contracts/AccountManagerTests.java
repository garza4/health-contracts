/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
//      PaymentDecisions decision = accountManager.manageMasterAccount();
//      System.out.println(decision.toString());
//      
//    }
    
}
