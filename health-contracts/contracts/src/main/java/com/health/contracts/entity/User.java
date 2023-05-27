package com.health.contracts.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name="h_user")
public class User {
	@Id
	String upkid;
    @Column(name="uid")
	String uName;
    @Column(name="role")
	String role;
    @Column(name="company")
	String company;

}
