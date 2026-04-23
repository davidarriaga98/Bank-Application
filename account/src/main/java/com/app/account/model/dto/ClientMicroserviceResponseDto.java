package com.app.account.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClientMicroserviceResponseDto {
    private Long id;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("identificacion")
    private String identification;
}
