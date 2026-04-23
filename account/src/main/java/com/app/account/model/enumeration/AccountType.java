package com.app.account.model.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AccountType {
    @JsonProperty("Ahorro")
    SAVINGS, // Ahorro

    @JsonProperty("Corriente")
    CHECKING // Corriente
}
