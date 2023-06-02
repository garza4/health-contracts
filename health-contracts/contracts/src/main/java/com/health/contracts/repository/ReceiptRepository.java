/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.health.contracts.repository;

import com.health.contracts.entity.FundReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bobby
 */
@Repository
public interface ReceiptRepository extends JpaRepository<FundReceiptEntity,String>{
    
}
