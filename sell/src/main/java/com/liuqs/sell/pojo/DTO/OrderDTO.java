package com.liuqs.sell.pojo.DTO;

import com.liuqs.sell.pojo.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description: 订单DTO
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Data
public class OrderDTO {
    /** 订单id */
    private String orderId;
    /** 买家名字 */
    private String buyerName;
    /** 买家电话 */
    private String buyerPhone;
    /** 买家地址 */
    private String buyerAddress;
    /** 买家微信openid */
    private String buyerOpenid;
    /** 订单总金额 */
    private BigDecimal orderAmount;
    /** 订单状态,默认为0新下单 */
    private Integer orderStatus;
    /** 支付状态,默认为0未支付 */
    private Integer payStatus;
    /** 订单详情 集合 **/
    List<OrderDetail> orderDetailList;
}
