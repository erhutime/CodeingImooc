package com.timeday.time.pojo;

import java.util.Date;

public class PromoGoods {
    private Integer id;

    private String promoName;

    private Date startDate;

    private Date endDate;

    private Integer itemId;

    private Double promoItemPrice;

    public PromoGoods(Integer id, String promoName, Date startDate, Date endDate, Integer itemId, Double promoItemPrice) {
        this.id = id;
        this.promoName = promoName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itemId = itemId;
        this.promoItemPrice = promoItemPrice;
    }

    public PromoGoods() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName == null ? null : promoName.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Double getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(Double promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}