package com.health.contracts.model;

import java.util.List;

import org.stellar.sdk.responses.AccountResponse.Balance;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BalanceList {
	@JsonProperty("balances")
	List<Balance> balances;

}
