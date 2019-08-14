package com.timeday.time.controller;
import com.timeday.time.controller.viewobject.GoodsVo;
import com.timeday.time.error.BusinessException;
import com.timeday.time.response.CommonReturnType;
import com.timeday.time.service.GoodsService;
import com.timeday.time.service.PromoService;
import com.timeday.time.service.model.GoodsModel;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/goods")
@RequestMapping("/goods")
public class GoodsController extends BaseController{

    @Autowired
    public GoodsService goodsService;

    @Autowired
    public PromoService promoService;

    @RequestMapping(value = "/createGoods")
    @ResponseBody
    public CommonReturnType createGoods(String title, String description, String imageUrl, BigDecimal price, Integer stock) throws BusinessException {
        GoodsModel goodsModel = new GoodsModel();
        goodsModel.setTitle(title);
        goodsModel.setDescription(description);
        goodsModel.setPrice(price);
        goodsModel.setStock(stock);
        goodsModel.setImageUrl(imageUrl);
        GoodsModel goodsModelReturn = goodsService.createGoods(goodsModel);
        GoodsVo goodsVo=convertVOFromModel(goodsModelReturn);
        return  CommonReturnType.create(goodsVo,"success");
    }


    @RequestMapping(value = "/getGoodInfo")
    @ResponseBody
    public CommonReturnType getGoodInfo(Integer id) {
        GoodsModel goodsModel = goodsService.getGoodsById(id);
        GoodsVo goodsVo = convertVOFromModel(goodsModel);
        return CommonReturnType.create(goodsVo,"success");
    }


    @RequestMapping(value = "/getGoodList")
    @ResponseBody
    public CommonReturnType goodsList() {
        List<GoodsModel> list = goodsService.goodsList();
       List<GoodsVo> goodsVos = list.stream().map(itemModel->{
             GoodsVo goodVo = this.convertVOFromModel(itemModel);
             return  goodVo;
        }).collect(Collectors.toList());
        return CommonReturnType.create(goodsVos,"success");
    }

    private GoodsVo convertVOFromModel(GoodsModel model){
        if(model == null){
            return  null;
        }
        GoodsVo goodsVo = new GoodsVo();
        BeanUtils.copyProperties(model,goodsVo);
        if(model.getPromoModel()!=null){
            goodsVo.setPromoStatus(model.getPromoModel().getStatus());
            goodsVo.setPromoId(model.getPromoModel().getId());
            goodsVo.setStartDate(model.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            goodsVo.setEndDate(model.getPromoModel().getEndDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            goodsVo.setPromoPrice(model.getPromoModel().getPromoItemPrice());
            goodsVo.setPromoName(model.getPromoModel().getPromoName());
        }else {
            goodsVo.setPromoStatus(0);
        }
       return goodsVo;
    }
}
