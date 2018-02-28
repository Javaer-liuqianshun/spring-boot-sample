package com.liuqs.sell.dao.mapper;

import com.liuqs.sell.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {


    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", "今日推荐");
        map.put("categoryType", 2);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }

    @Test
    public void insertByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("明日推荐");
        productCategory.setCategoryType(3);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByCategoryType() throws Exception {
        ProductCategory productCategory = mapper.findByCategoryType(6);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void selectByCategoryType() throws Exception {
        ProductCategory productCategory = mapper.selectByCategoryType(6);
        System.out.println(productCategory);
        Assert.assertNotNull(productCategory);
    }
}