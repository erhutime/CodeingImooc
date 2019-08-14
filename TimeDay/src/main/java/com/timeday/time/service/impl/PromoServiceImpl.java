package com.timeday.time.service.impl;

import com.timeday.time.dao.PromoGoodsMapper;
import com.timeday.time.pojo.PromoGoods;
import com.timeday.time.service.PromoService;
import com.timeday.time.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PromoServiceImpl implements PromoService{

    @Autowired
    private PromoGoodsMapper promoItemMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        PromoGoods promoItem = promoItemMapper.selectByItemId(itemId);
        PromoModel promoModel = convertFromDataObject(promoItem);
        //判断当前活动的状态
        if(promoModel == null){
            return  null;
        }
        if(promoModel.getStartDate().isAfterNow()){
            promoModel.setStatus(1);
        }else if(promoModel.getEndDate().isBeforeNow()){
            promoModel.setStatus(3);
        }else {
            promoModel.setStatus(2);
        }
        return promoModel;
    }


    private PromoModel convertFromDataObject(PromoGoods promoItem){
        if(promoItem == null){
            return  null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoItem,promoModel);
        promoModel.setPromoItemPrice(new BigDecimal(promoItem.getPromoItemPrice()));
        promoModel.setStartDate(new DateTime(promoItem.getStartDate()));
        promoModel.setEndDate(new DateTime(promoItem.getEndDate()));
        return  promoModel;
    }
}
