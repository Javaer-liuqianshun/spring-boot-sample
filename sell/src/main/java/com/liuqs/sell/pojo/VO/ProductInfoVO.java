package com.liuqs.sell.pojo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ Author: liuqianshun
 * @ Description: 商品详情
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private String productDescription;
    @JsonProperty("icon")
    private String productIcon;

}
