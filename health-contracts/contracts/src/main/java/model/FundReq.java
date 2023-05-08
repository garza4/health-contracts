package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shadow.org.jetbrains.annotations.NotNull;
@Getter
@Setter
@ToString
public class FundReq {
	@JsonProperty
	@NotNull
	String amount;
	
	@NotNull
	@JsonProperty
	String id;
	
	@JsonProperty
	String secretSeed;
}
