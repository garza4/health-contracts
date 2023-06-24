/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.health.contracts.repository;

import com.health.contracts.entity.FundReceiptEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bobby
 */
@Repository
public interface ReceiptRepository extends JpaRepository<FundReceiptEntity,String>{
    @Query(value="select * from h_receipt where status=P",nativeQuery=true)
    List<FundReceiptEntity> getPendingReceipts(); 
}
