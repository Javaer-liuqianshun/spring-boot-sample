package com.liuqs.sell.pojo.VO;

import lombok.Data;


import java.math.BigDecimal;

/**
 * @ Author: liuqianshun
 * @ Description:卖家端商品表单
 * @ Date: Created in 2018-01-08
 * @ Modified:
 **/
@Data
public class ProductFormVO {
    private String productId;
    // 名字
    private String productName;
    // 单价
    private BigDecimal productPrice;
    // 库存
    private Integer productStock;
    // 描述
    private String productDescription;
    // 小图
    private String productIcon;
    // 类目编号
    private Integer categoryType;

}
