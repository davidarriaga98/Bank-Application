package com.app.client.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @Length(max = 50)
    @NotBlank
    private String name;

    @NotBlank
    private String gender;

    @NotNull
    private Short age;

    @Length(max = 20)
    @NotBlank
    @Pattern(regexp = "^[0-9]+$")
    private String identification;

    @Length(max = 255)
    @NotBlank
    private String address;

    @Length(max = 25)
    @NotBlank
    @Pattern(regexp = "^[0-9]+$")
    private String phone;

    @Length(max = 255)
    @NotBlank
    private String password;

    @NotNull
    private Boolean status;
}
