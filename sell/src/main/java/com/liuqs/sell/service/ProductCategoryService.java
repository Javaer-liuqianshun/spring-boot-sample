package com.liuqs.sell.service;

import com.liuqs.sell.pojo.ProductCategory;

import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description: 商品类目 Service 接口
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
public interface ProductCategoryService {

    ProductCategory findOne(Integer categroyId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
