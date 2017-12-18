package com.liuqs.sell.service.impl;

import com.liuqs.sell.enums.ProductStatusEnum;
import com.liuqs.sell.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoServiceImpl;

    @Test
    public void findOne() throws Exception {
        ProductInfo result = productInfoServiceImpl.findOne("123456");
        Assert.assertEquals("123456",result.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> result = productInfoServiceImpl.findUpAll();
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> pages = productInfoServiceImpl.findAll(pageRequest);
        System.out.println(pages.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234567");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(56.3));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("调皮!皮皮虾");
        productInfo.setProductIcon("http://www.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(3);
        ProductInfo result = productInfoServiceImpl.save(productInfo);
        Assert.assertNotNull(result);
    }

}