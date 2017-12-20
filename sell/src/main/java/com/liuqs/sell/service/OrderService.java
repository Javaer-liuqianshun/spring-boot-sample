package com.liuqs.sell.service;

import com.liuqs.sell.pojo.DTO.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description: 订单 Service 接口
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    OrderDTO  findOne(String orderId);

    /**
     * 根据openid分页查询订单
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    OrderDTO paid(OrderDTO orderDTO);
}
