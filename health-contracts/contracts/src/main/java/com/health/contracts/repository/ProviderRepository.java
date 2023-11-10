package com.health.contracts.repository;

import com.health.contracts.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProviderRepository extends JpaRepository<ProviderEntity,Integer> {
    @Query(value="SELECT * FROM h_provider_info where h_provider_name=:providerName",nativeQuery = true)
    ProviderEntity getAccountId(@Param("providerName") String providerName);
}
