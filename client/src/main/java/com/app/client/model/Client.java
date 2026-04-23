package com.app.client.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Client extends Person {
    private String password;
    private Boolean status;
}
