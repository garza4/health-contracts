package com.health.contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.contracts.entity.VisitationEntity;

@Repository
public interface AdminRepository extends JpaRepository<VisitationEntity,String>{

}
