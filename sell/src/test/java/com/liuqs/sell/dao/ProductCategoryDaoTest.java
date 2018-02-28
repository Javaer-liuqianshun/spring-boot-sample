package com.liuqs.sell.dao;

import com.liuqs.sell.pojo.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description: ProductCategoryDao单元测试
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    /**
     * 查询一条商品类目记录
     */
    @Test
    public void findOneTest(){
        ProductCategory productCategory = productCategoryDao.findOne(1);
        System.out.println(productCategory.toString());
    }

    /**
     * 保存商品类目
     */
    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        productCategoryDao.save(productCategory);
    }

    /**
     * 更新商品类目,也是用save()方法
     * 更新一般先查询再更新
     */
    @Test
    public void updateTest(){
        ProductCategory productCategory = productCategoryDao.findOne(2);
        productCategory.setCategoryType(3);
        productCategoryDao.save(productCategory);
    }

    /**
     * jpa通过方法名确定sql
     */
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> result = productCategoryDao.findByCategoryTypeIn(list);
        System.out.println(result);
    }
}