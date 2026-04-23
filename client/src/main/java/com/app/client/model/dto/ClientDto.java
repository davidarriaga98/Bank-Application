package com.app.client.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;

    @Length(max = 50)
    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private String gender;

    private short age;

    @Length(max = 20)
    @NotEmpty
    @NotNull
    @Pattern(regexp = "^[0-9]+$")
    private String identification;

    @Length(max = 255)
    @NotEmpty
    @NotNull
    private String address;

    @Length(max = 25)
    @NotEmpty
    @NotNull
    @Pattern(regexp = "^[0-9]+$")
    private String phone;

    @Length(max = 255)
    @NotEmpty
    @NotNull
    private String password;

    @NotNull
    private Boolean status;
}
