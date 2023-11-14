package com.example.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Data
public class Item implements Serializable {
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

    public String getImg() {
        return imgs.get(0);
    }

    public String getLink() {
        return "/details/" + id;
    }

    public void setLink() {
    }

}









