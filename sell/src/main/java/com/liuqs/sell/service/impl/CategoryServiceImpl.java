package com.liuqs.sell.service.impl;

import com.liuqs.sell.dao.ProductCategoryDao;
import com.liuqs.sell.pojo.ProductCategory;
import com.liuqs.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description: 商品类目 Service 实现类
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findOne(Integer categroyId) {
        return productCategoryDao.findOne(categroyId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryDao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }
}
