package com.timeday.time.controller;

import com.timeday.time.error.BusinessException;
import com.timeday.time.response.CommonReturnType;
import com.timeday.time.service.OrderService;
import com.timeday.time.service.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/order")
@RequestMapping("/order")
public class OrderController extends BaseController {
   @Autowired
   private OrderService orderService;

    @RequestMapping(value = "/createOrder")
    @ResponseBody
   public CommonReturnType createOrder(Integer goodId, Integer promoId, Integer amount,Integer userId) throws BusinessException {
     OrderModel orderModel = orderService.createOrder(userId,goodId,promoId,amount);
     return  CommonReturnType.create(orderModel,"success");
   }
}
