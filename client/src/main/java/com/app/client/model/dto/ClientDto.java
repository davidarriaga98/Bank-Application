package com.app.client.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;

    @JsonProperty("nombre")
    @Length(max = 50)
    @NotBlank
    private String name;

    @JsonProperty("genero")
    @NotBlank
    private String gender;

    @JsonProperty("edad")
    @NotNull
    private Short age;

    @JsonProperty("identificacion")
    @Length(max = 20)
    @NotBlank
    @Pattern(regexp = "^[0-9]+$")
    private String identification;

    @JsonProperty("direccion")
    @Length(max = 255)
    @NotBlank
    private String address;

    @JsonProperty("telefono")
    @Length(max = 25)
    @NotBlank
    @Pattern(regexp = "^[0-9]+$")
    private String phone;

    @JsonProperty("contrasena")
    @Length(max = 255)
    @NotBlank
    private String password;

    @JsonProperty("estado")
    @NotNull
    private Boolean status;
}
