package com.timeday.time.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 商品
 */
public class GoodsModel {
    private Integer id;
    @NotBlank(message = "商品名称不能为空")
    private String title;
    //商品价格
    @NotNull(message = "商品价格不能为空")
    @Min(value = 0,message = "商品价格必须大于0")
    private BigDecimal price;
    //商品库存
    @NotNull(message = "商品库存必须大于0")
    private Integer stock;
    //商品描述
    @NotNull(message = "商品描述信息不能为空")
    private String description;
    //商品销量
    private Integer sales;
    //商品描述图片
    @NotNull(message = "商品图片信息不能为空")
    private String imageUrl;

    //聚合模型，如果promoModel不为空，则表示该商品存在还未结束的活动
    private PromoModel promoModel;

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
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
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        this.imageUrl = imageUrl;
    }
}
