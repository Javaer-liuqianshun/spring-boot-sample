package com.liuqs.sell.service.impl;

import com.liuqs.sell.pojo.SellerInfo;
import com.liuqs.sell.service.SellerInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {

    private static final String openid = "abc";

    @Autowired
    private SellerInfoService sellerInfoService;

    @Test
    public void findSellerInfoByOpenid() throws Exception {
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(openid);
        Assert.assertEquals("abc",sellerInfo.getOpenid());
    }

}