package com.health.contracts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StellarAccount {

	@JsonProperty("secret")
	String secret;

	@JsonProperty("acct_id")
	String accountId;

	@JsonProperty
	String message;

}
