package com.health.contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.contracts.entity.VisitationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AdminRepository extends JpaRepository<VisitationEntity,String>{
    @Query(value="SELECT * FROM h_visitation_log where provider=:provider and status='w'",nativeQuery=true)
    List<VisitationEntity> getVisitations(@Param("provider") String provider);

    @Query(value="SELECT * FROM h_visitation_log where provider=:provider and status='p'",nativeQuery = true)
    List<VisitationEntity> getCompletedVisitations(@Param("provider") String provider);
}
