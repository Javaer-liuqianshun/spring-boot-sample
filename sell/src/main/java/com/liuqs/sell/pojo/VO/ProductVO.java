package com.liuqs.sell.pojo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description:商品(包含类目)
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categorytype;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
