package com.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    private String id;
    /**
     * 0: admin;
     * 1: client;
     */
    private String username;
    private String password;
    private String avatar;
    private String phone;
    private Integer role;
}
