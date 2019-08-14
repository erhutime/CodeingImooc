package com.timeday.time.service.impl;

import com.timeday.time.dao.OrderInfoMapper;
import com.timeday.time.dao.SequenceInfoMapper;
import com.timeday.time.error.BusinessException;
import com.timeday.time.error.EmBusinessError;
import com.timeday.time.pojo.OrderInfo;
import com.timeday.time.pojo.SequenceInfo;
import com.timeday.time.service.GoodsService;
import com.timeday.time.service.OrderService;
import com.timeday.time.service.UserService;
import com.timeday.time.service.model.GoodsModel;
import com.timeday.time.service.model.OrderModel;
import com.timeday.time.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private SequenceInfoMapper sequenceInfoMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId,Integer promoId, Integer amount) throws BusinessException {
        //校验下单状态，下单商品是否存在，用户是否合法，购买数量是否存在
        GoodsModel goodsModel = goodsService.getGoodsById(itemId);
        if(goodsModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商品不存在");
        }
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户不存在，请重新登录");
        }
        if(amount<=0||amount>99){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"购买信息不正确，请重新输入");
        }
        //落单减库存
        boolean result = goodsService.decreaseStock(itemId,amount);
        if(!result){
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }
        //活动信息
        if(promoId != null){
           if(promoId.intValue()!=goodsModel.getPromoModel().getId()){
               throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"活动信息不正确");
           }else if(goodsModel.getPromoModel().getStatus().intValue()!=2){
               throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"活动还未开始");
           }
        }
        OrderModel orderModel = new OrderModel();
        orderModel.setAmount(amount);
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setPromoId(promoId);
        if(promoId!=null){
            orderModel.setItemPrice(goodsModel.getPromoModel().getPromoItemPrice());
        }else {
            orderModel.setItemPrice(goodsModel.getPrice());
        }
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));
        //生产订单号
        orderModel.setId(generateOrderNo());
        OrderInfo orderInfo = convertFromOrderModel(orderModel);
        orderInfoMapper.insertSelective(orderInfo);
        //增加商品销量
        goodsService.increaseSales(itemId,amount);
        return orderModel;
    }


    private OrderInfo convertFromOrderModel(OrderModel orderModel){
        if(orderModel== null){
            return  null;
        }
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderModel,orderInfo);
        orderInfo.setItemPrice(orderModel.getItemPrice().doubleValue());
        orderInfo.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        return  orderInfo;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateOrderNo(){
        StringBuilder stringBuilder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.BASIC_ISO_DATE).replace("-","");
        stringBuilder.append(nowDate);
        int sequence;
        SequenceInfo sequenceInfo =sequenceInfoMapper.getSequenceByName("order_info");
        sequence = sequenceInfo.getCurrentValue();
        sequenceInfo.setCurrentValue(sequenceInfo.getCurrentValue()+sequenceInfo.getStep());
        sequenceInfoMapper.updateByPrimaryKeySelective(sequenceInfo);
        String sequenceMsg= String.valueOf(sequence);
        for (int i=0;i<6- sequenceMsg.length();i++){
            stringBuilder.append(0);
        }
        stringBuilder.append(sequence);
        stringBuilder.append("00");
        return stringBuilder.toString();
    }
}
