package com.timeday.time.service;

import com.timeday.time.error.BusinessException;
import com.timeday.time.service.model.GoodsModel;

import java.util.List;

public interface GoodsService {
    //创建商品
    GoodsModel createGoods(GoodsModel goodsModel) throws BusinessException;

    //商品列表
    List<GoodsModel> goodsList();

    //商品详情
    GoodsModel getGoodsById(Integer id);

    //库存减少
    boolean decreaseStock(Integer itemId,Integer amount) throws BusinessException ;

    //增加销量
    void increaseSales(Integer itemId,Integer amount) throws BusinessException ;
}
