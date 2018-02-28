package com.liuqs.sell.dao.mapper;

import com.liuqs.sell.pojo.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.Map;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-27
 * @ Modified:
 **/
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    @Select("select *from  product_category where category_type = #{categoryType,jdbcType=INTEGER }")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
    })
    ProductCategory findByCategoryType(Integer categoryType);

    ProductCategory selectByCategoryType(@Param("categoryType") Integer categoryType);
}
