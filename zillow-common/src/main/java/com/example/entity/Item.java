package com.example.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Data
public class Item {
    private String id;
    private String title;
    private Long sales;
    private Boolean recommendation;
    private Byte weight;
    private String city;
    private Long price;
    private String rentType;
    private String aptType;
    private Map<String, String> info;
    private List<String> imgs;
    private Date buytime;
    private Boolean isRented;



}









