package com.liuqs.sell.service.impl;

import com.liuqs.sell.enums.ResultEnum;
import com.liuqs.sell.exception.SellException;
import com.liuqs.sell.pojo.DTO.OrderDTO;
import com.liuqs.sell.service.BuyerService;
import com.liuqs.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2017-12-20
 * @ Modified:
 **/
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOneOrder(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (null == orderDTO){
            log.error("【取消订单】查不到改订单, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderDTO cancelResult = orderService.cancel(orderDTO);
        return cancelResult;
    }

    /**
     * 判断订单的openid和买家openid是否一致
     * 一致返回查询的订单
     * @param openid
     * @param orderId
     * @return
     */
    private OrderDTO checkOrderOwner(String openid,String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (null == orderDTO){
            return null;
        }
        if (!openid.equals(orderDTO.getBuyerOpenid())){
            log.error("【查询订单】订单的openid和买家openid不一致.买家openid={},订单信息orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
