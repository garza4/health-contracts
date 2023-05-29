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
@JsonPropertyOrder({"uid","comments","service","req_funds"})
public class LogVisitationReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("uid")
	String uid;
	@JsonProperty("comments")
	String comments;
	@JsonProperty("service")
	String service;
	@JsonProperty("req_funds")
	String reqForFundsUSD;

}
