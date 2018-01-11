package com.liuqs.sell.service;

import com.liuqs.sell.pojo.ProductCategory;

import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description: 商品类目 Service 接口
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
public interface CategoryService {

    /**
     * 查询单个商品类目信息
     * @param categroyId
     * @return
     */
    ProductCategory findOne(Integer categroyId);

    /**
     * 查询所有商品类目
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 根据类目编号查询商品类目
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 保存单个商品类目信息,
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
