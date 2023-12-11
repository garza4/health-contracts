package com.health.contracts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shadow.org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AddUserToSystemReq implements Serializable {
    @JsonProperty("user_name")
    @NotNull
    String userName;
    @NotNull
    String password;
    @JsonProperty("org_name")
    @NotNull
    String orgName;
    @JsonProperty("first_name")
    @NotNull
    String firstName;
    @JsonProperty("last_name")
    @NotNull
    String lastName;
}
