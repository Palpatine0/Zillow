package com.example.entity;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Map;


@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Item {
    private String id;
    private String title;
    private Long sales;
    private Boolean recommendation;
    private Byte weight;
    private Long price;
    private String city;
    private String rentType;
    private String houseType;
    private Map<String, String> info;
    private List<String> imgs;
    private Date buytime;
    private Boolean isRented;


    public Boolean getIsRented() {
        return isRented;
    }

    public void setIsRented(Boolean rented) {
        isRented = rented;
    }

    public Date getBuytime() {
        return buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return imgs.get(0);
    }

    public void setImg(String img) {
    }

    public String getLink() {
        return "/item/" + id;
    }

    public void setLink(String link) {
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Boolean getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Boolean recommendation) {
        this.recommendation = recommendation;
    }

    public Byte getWeight() {
        return weight;
    }

    public void setWeight(Byte weight) {
        this.weight = weight;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

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

    public Boolean getRented() {
        return isRented;
    }

    public void setRented(Boolean rented) {
        isRented = rented;
    }

}
