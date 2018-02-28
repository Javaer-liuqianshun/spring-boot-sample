package com.liuqs.sell.dao;

import com.liuqs.sell.pojo.SellerInfo;
import com.liuqs.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Test
    public void saveSellerInfoTest() throws Exception{
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");
        SellerInfo result = sellerInfoDao.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenIdTest() throws Exception {
        SellerInfo sellerInfo = sellerInfoDao.findByOpenid("abc");
        Assert.assertEquals("abc",sellerInfo.getOpenid());
    }

}