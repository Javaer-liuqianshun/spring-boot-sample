package com.liuqs.sell.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ Author: liuqianshun
 * @ Description:订单详情实体类
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Entity
@Data
public class OrderDetail {

    /** 订单详情id */
    @Id
    private String detailId;
    /** 订单id */
    private String orderId;
    /** 商品id */
    private String productId;
    /** 商品名称 */
    private String productName;
    /** 商品价格,单位分 */
    private BigDecimal productPrice;
    /** 数量 */
    private Integer productQuantity;
    /** 小图 */
    private String productIcon;
}
