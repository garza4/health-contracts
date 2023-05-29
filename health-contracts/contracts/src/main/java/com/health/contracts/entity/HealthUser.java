package com.health.contracts.entity;


import java.math.BigInteger;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="h_user")
@Getter
@Setter
@ToString
@Builder
public class HealthUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="upkid",columnDefinition = "BIGINT")
	BigInteger upkid;
    @Column(name="uid")
	String uName;
    @Column(name="role")
	String role;
    @Column(name="company")
	String company;
    @Column(name="first_name")
    String firstName;
    @Column(name="last_name")
    String lastName;

}
