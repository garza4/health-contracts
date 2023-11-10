package com.health.contracts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="h_provider_info")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProviderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    Long h_id;

    @Column(name="h_provider_name")
    String providerName;

    @Column(name="acct_id")
    String acctId;
}
