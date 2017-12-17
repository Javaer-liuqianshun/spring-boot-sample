package com.liuqs.sell.dao;

import com.liuqs.sell.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("小鸡炖蘑菇");
        productInfo.setProductPrice(new BigDecimal(33.3));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的小吃");
        productInfo.setProductIcon("http://www.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        ProductInfo result = productInfoDao.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatusTest(){
        List<ProductInfo> list = productInfoDao.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
    }
}