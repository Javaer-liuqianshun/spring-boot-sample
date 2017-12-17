package com.liuqs.sell.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ Author: liuqianshun
 * @ Description: 商品类目实体类
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
@Entity // 声明是一个实体类
@DynamicUpdate // 数据库更新只更新修改的字段
@Data //lombok的@Data注解,生成get、set和toString
public class ProductCategory {

    // 类目id
    @Id // 声明是主键id
    @GeneratedValue // 声明是自增长类型
    private Integer categoryId;
    // 类目名字
    private String categoryName;
    // 类目编号
    private Integer categoryType;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
