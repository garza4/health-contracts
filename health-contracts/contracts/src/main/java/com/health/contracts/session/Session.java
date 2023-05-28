package com.health.contracts.session;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleName;
	private long expires;
	
}
