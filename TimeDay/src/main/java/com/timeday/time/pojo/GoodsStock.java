package com.timeday.time.pojo;

public class GoodsStock {
    private Integer id;

    private Integer stock;

    private Integer itemId;

    public GoodsStock(Integer id, Integer stock, Integer itemId) {
        this.id = id;
        this.stock = stock;
        this.itemId = itemId;
    }

    public GoodsStock() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}