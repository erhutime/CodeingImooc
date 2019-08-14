package com.timeday.time.service.impl;

import com.timeday.time.dao.GoodsMapper;
import com.timeday.time.dao.GoodsStockMapper;
import com.timeday.time.error.BusinessException;
import com.timeday.time.error.EmBusinessError;
import com.timeday.time.pojo.Goods;
import com.timeday.time.pojo.GoodsStock;
import com.timeday.time.service.PromoService;
import com.timeday.time.service.model.GoodsModel;
import com.timeday.time.service.GoodsService;
import com.timeday.time.service.model.PromoModel;
import com.timeday.time.validator.ValidationResult;
import com.timeday.time.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private PromoService promoService;

    @Autowired
    private GoodsStockMapper goodsStockMapper;

    @Override
    @Transactional
    public GoodsModel createGoods(GoodsModel goodsModel) throws BusinessException {
        ValidationResult result = validator.validate(goodsModel);
        if(result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }
        Goods goods = this.convertItemFromItemModel(goodsModel);
        goodsMapper.insertGoods(goods);
        goodsModel.setId(goods.getId());
        GoodsStock goodsStock = this.convertItemStockFromItemModel(goodsModel);
        goodsStockMapper.insertGoodsStock(goodsStock);
        return this.getGoodsById(goodsModel.getId());
    }

    @Override
    public List<GoodsModel> goodsList() {
       List<Goods> items = goodsMapper.goodsList();
       List<GoodsModel> goodsModels= items.stream().map(item -> {
           GoodsStock goodsStock = goodsStockMapper.selectByItemId(item.getId());
           GoodsModel goodsModel = this.convertModelFromDataObject(item,goodsStock);
           return goodsModel;
        }).collect(Collectors.toList());
        return goodsModels;
    }

    @Override
    public GoodsModel getGoodsById(Integer id) {
        Goods goods = goodsMapper.selectGoodsById(id);
        if(goods == null){
            return  null;
        }
        GoodsStock goodsStock = goodsStockMapper.selectByItemId(goods.getId());
        GoodsModel itemModel = this.convertModelFromDataObject(goods,goodsStock);
        PromoModel promoModel = promoService.getPromoByItemId(id);
        if(promoModel != null&&promoModel.getStatus().intValue()!=3){
            itemModel.setPromoModel(promoModel);
        }
        return itemModel;
    }


    @Override
    public boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException {
        int affectedRow = goodsStockMapper.decreaseStock(itemId,amount);
        if(affectedRow >0){
             return  true;
         }else {
            return false;
        }
    }


    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount)  {
        goodsMapper.increaseSales(itemId,amount);
    }

    private Goods convertItemFromItemModel(GoodsModel model){
        if (model == null){
            return  null;
        }
        Goods goods = new Goods();
        BeanUtils.copyProperties(model,goods);
        goods.setPrice(model.getPrice().doubleValue());
        return goods;
    }

    private GoodsStock convertItemStockFromItemModel(GoodsModel model){
        if (model == null){
            return  null;
        }
        GoodsStock goodsStock = new GoodsStock();
        goodsStock.setItemId(model.getId());
        goodsStock.setStock(model.getStock());
        return goodsStock;
    }

    private GoodsModel convertModelFromDataObject(Goods item, GoodsStock itemStock){
        GoodsModel goodsModel = new GoodsModel();
        BeanUtils.copyProperties(item,goodsModel);
        goodsModel.setPrice(new BigDecimal(item.getPrice()));
        goodsModel.setStock(itemStock.getStock());
        return goodsModel;
    }
}
