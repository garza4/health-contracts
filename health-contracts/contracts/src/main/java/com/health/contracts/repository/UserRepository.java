package com.health.contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.contracts.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	User findByUid(String uid);

}
