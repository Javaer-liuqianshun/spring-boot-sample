package com.liuqs.sell.pojo.DTO;

import lombok.Data;

/**
 * @ Author: liuqianshun
 * @ Description:购物车DTO
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Data
public class CartDTO {

    /** 商品id */
    private String productId;
    /** 商品数量 */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
