package com.liuqs.sell.pojo;

import com.liuqs.sell.enums.OrderStatusEnum;
import com.liuqs.sell.enums.PayStatusEnum;
import com.liuqs.sell.enums.ResultEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ Author: liuqianshun
 * @ Description: 订单实体类
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Entity
@Data
public class OrderMaster {
    /** 订单id */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /** 支付状态,默认为0未支付 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
}
