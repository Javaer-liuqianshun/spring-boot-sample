package com.liuqs.sell.service;

import com.liuqs.sell.pojo.DTO.OrderDTO;

/**
 * @ Author: liuqianshun
 * @ Description: 买家 Service 接口
 * 接口目的:买家只能查询自己的订单,即订单中的openid和该买家的微信openid一致
 * @ Date: Created in 2017-12-20
 * @ Modified:
 **/
public interface BuyerService {

    /**
     * 根据openid和orderId查询订单详情
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOneOrder(String openid,String orderId);

    /**
     * 根据openid和orderId取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid,String orderId);
}
