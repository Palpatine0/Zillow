package com.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginLog {
    private String id;
    private String username;
    private String type;
    private Date loginTime;
    private boolean isSuccess;
    private String message;
}