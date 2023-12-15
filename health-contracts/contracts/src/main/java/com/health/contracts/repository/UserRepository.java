package com.health.contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.health.contracts.entity.HealthUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<HealthUser,Long>{
    @Query(value="select * from h_user where uid=:uid",nativeQuery=true)
    HealthUser getUserByUName(@Param("uid") String uid);

    @Query(value="select * from h_user where uid=:uid",nativeQuery=true)
    List<HealthUser> getUserByName(@Param("uid") String uid);

    @Query(value="select * from h_user where uid=:unm and password=:pwd",nativeQuery=true)
    HealthUser getUserByUNameAndPwd(@Param("unm") String unm, @Param("pwd") String pwd);
    
}
