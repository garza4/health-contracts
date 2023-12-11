package com.health.contracts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AddUserToSystemResp implements Serializable {
    @JsonProperty("acctInfo")
    StellarAccount stellarAccount;
    String status;

}
