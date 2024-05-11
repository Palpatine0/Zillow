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
public class Order {
    private String id;
    private String userId;
    private String itemId;
    private String userName;
    private String title;
    private String price;
    private Date startDate;
    private Date endDate;
}