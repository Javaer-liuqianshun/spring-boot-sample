package com.liuqs.sell.dao;

import com.liuqs.sell.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description: 商品 Dao 接口
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

        List<ProductInfo> findByProductStatus(Integer productStatus);

        List<ProductInfo> findByCategoryType(Integer categoryType);
}
