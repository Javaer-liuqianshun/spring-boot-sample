package com.liuqs.sell.service.impl;

import com.liuqs.sell.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl CategoryServiceImpl;

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = CategoryServiceImpl.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> list = CategoryServiceImpl.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> list = CategoryServiceImpl.findByCategoryTypeIn(Arrays.asList(1, 2, 3));
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("女生热爱",4);
        ProductCategory result = CategoryServiceImpl.save(productCategory);
        Assert.assertNotNull(result);
    }

}