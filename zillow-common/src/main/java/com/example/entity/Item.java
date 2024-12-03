package com.example.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Item {
    private String id;
    private String title;
    private String description;
    private Long sales;
    private Boolean recommendation;
    private Byte weight;
    private Long price;
    private String city;
    /**
     *  Whole Rental
     *  Room Rental
     */
    private String rentType;
    private String houseType;
    private Map<String, String> info;
    private List<String> imgs;
    private Date buytime;
    private Boolean isRented;



    public String getHouseType4Search() {
        return info.get("level") + " | " + info.get("type") + " - " + houseType;
    }

    public void setHouseType4Search(String houseType4Search) {
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }


}
