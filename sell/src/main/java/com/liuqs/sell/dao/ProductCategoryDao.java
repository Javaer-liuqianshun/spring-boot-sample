package com.liuqs.sell.dao;

import com.liuqs.sell.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description:商品类目 Dao 接口
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer>{

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
