package com.health.contracts.entity;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="h_visitation_log")
@Getter
@Setter
@Builder
public class VisitationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT")
	Long visitRef;
	
	@Column(name="uid")
	String uid;
        
	@Column(name="comments")
	String comments;
	
	@Column(name="service")
	String service;
	
	@Column(name="requested_funds")
	String requestedFunds;
        
        @Column(name="upkid")
        Long upkid;

}
