package com.timeday.time.service;

import com.timeday.time.error.BusinessException;
import com.timeday.time.service.model.OrderModel;

public interface OrderService {
    //创建订单
    OrderModel createOrder(Integer userId,Integer itemId,Integer promoId,Integer amount) throws BusinessException;
}
