package com.health.contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.contracts.entity.HealthUser;
@Repository
public interface UserRepository extends JpaRepository<HealthUser,Long>{
	HealthUser findByUid(String uid);

}
