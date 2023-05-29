package com.health.contracts.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LogVisitationReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String uid;
	String comments;
	String service;
	String reqForFundsUSD;

}
