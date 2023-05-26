package com.health.contracts.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonPropertyOrder({"amount","id","secret"})
public class FundReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("amount")
	String amount;

	@JsonProperty("id")
	String id;
	
	@JsonProperty("source_secret")
	String sourceSecret;
	
	@JsonProperty("source_public")
	String publicMasterAccount;
}
