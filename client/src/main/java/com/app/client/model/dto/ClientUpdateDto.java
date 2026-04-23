package com.app.client.model.dto;

import lombok.Data;

@Data
public class ClientUpdateDto {
    private String name;
    private String gender;
    private Short age;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private Boolean status;
}
