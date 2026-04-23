package com.app.client.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientUpdateDto {
    @JsonProperty("nombre")
    private String name;

    @JsonProperty("genero")
    private String gender;

    @JsonProperty("edad")
    private Short age;

    @JsonProperty("identificacion")
    private String identification;

    @JsonProperty("direccion")
    private String address;

    @JsonProperty("telefono")
    private String phone;

    @JsonProperty("contrasena")
    private String password;

    @JsonProperty("estado")
    private Boolean status;
}
