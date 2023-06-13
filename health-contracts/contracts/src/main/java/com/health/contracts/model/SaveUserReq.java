package com.health.contracts.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonPropertyOrder({"company","l_nm","f_nm","role","u_nm","password"})
public class SaveUserReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("company")
	String company;
	@JsonProperty("l_nm")
	String lastName;
	@JsonProperty("f_nm")
	String firstName;
	@JsonProperty("role")
	String role;
	@JsonProperty("u_nm")
	String uName;
        @JsonProperty("password")
        String password;
	
}
