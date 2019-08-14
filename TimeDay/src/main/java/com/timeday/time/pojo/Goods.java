package com.timeday.time.pojo;

public class Goods {
    private Integer id;

    private String title;

    private Double price;

    private String description;

    private Integer sales;

    private String imageUrl;

    public Goods(Integer id, String title, Double price, String description, Integer sales, String imageUrl) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.sales = sales;
        this.imageUrl = imageUrl;
    }

    public Goods() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }
}