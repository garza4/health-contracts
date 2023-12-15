package com.health.contracts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="h_user")
@Getter
@Setter
@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthUser implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long upkid;
    
    @Column(name="uid")
    String uName;
    
    @Column(name="role")
    String role;
    
    @Column(name="provider")
    String provider;
    
    @Column(name="first_name")
    String firstName;
    
    @Column(name="last_name")
    String lastName;
    
    @Column(name="password")
    String password;

    @Column(name="public_address")
    String publicAddress;
}
