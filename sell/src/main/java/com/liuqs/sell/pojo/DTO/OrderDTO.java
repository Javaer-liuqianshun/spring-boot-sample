package com.liuqs.sell.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.liuqs.sell.enums.OrderStatusEnum;
import com.liuqs.sell.enums.PayStatusEnum;
import com.liuqs.sell.pojo.OrderDetail;
import com.liuqs.sell.utils.EnumUtil;
import com.liuqs.sell.utils.serializer.CustomDateSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description: 订单DTO
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
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
    /** 订单状态 */
    private Integer orderStatus;
    /** 支付状态 */
    private Integer payStatus;
    /** 创建时间 */
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;
    /** 更新时间 */
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updateTime;
    /** 订单详情 集合 **/
    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getEnumByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getEnumByCode(payStatus,PayStatusEnum.class);
    }
}
