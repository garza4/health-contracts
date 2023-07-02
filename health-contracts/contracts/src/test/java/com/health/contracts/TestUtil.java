/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.health.contracts;

import com.health.contracts.entity.FundReceiptEntity;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Bobby
 */
public class TestUtil {
    
    public List<FundReceiptEntity> getReceipts(){
        var receipts =  new ArrayList<>(Arrays.asList(
                new FundReceiptEntity(Long.valueOf(1),new Timestamp(System.currentTimeMillis()),"19","cooper","opal","P"),
                new FundReceiptEntity(Long.valueOf(2),new Timestamp(System.currentTimeMillis()),"27","cooper","doodle","P"),
                new FundReceiptEntity(Long.valueOf(3),new Timestamp(System.currentTimeMillis()),"28","cooper","pearl","P"),
                new FundReceiptEntity(Long.valueOf(4),new Timestamp(System.currentTimeMillis()),"29","cooper","mario","P"))
        );
        return receipts;
    }
    
}
