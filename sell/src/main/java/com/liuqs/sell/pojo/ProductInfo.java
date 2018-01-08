package com.liuqs.sell.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liuqs.sell.enums.ProductStatusEnum;
import com.liuqs.sell.utils.EnumUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ Author: liuqianshun
 * @ Description: 商品实体类
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
@Entity
@Data
public class ProductInfo {

    // 商品id
    @Id
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
    // 状态 0正常,1下架
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    // 类目编号
    private Integer categoryType;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getEnumByCode(productStatus,ProductStatusEnum.class);
    }

}
