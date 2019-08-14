package com.timeday.time.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public class PromoModel {
    private Integer id;
    //活动名称
    private String promoName;
    //活动状态 1 表示未开始 2表示进行中 3表示已结束
    private Integer status;
    //秒杀活动开始时间
    private DateTime startDate;

    //秒杀活动开始时间
    private DateTime endDate;
    //秒杀活动商品ID
    private Integer itemId;
    //秒杀活动商品价格
    private BigDecimal promoItemPrice;


    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(BigDecimal promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}
