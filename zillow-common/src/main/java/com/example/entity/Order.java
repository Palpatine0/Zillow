package com.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Order {
    private String id;
    private String phone;
    private String itemId;
    private String title;
    private String houseType;
    private String rentType;
    private String price;
    private String img;
    private int commentState;
}