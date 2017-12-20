package com.liuqs.sell.dao;

import com.liuqs.sell.pojo.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @ Author: liuqianshun
 * @ Description: OrderMasterDao单元测试
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    private final String OPENID = "abc123";

    @Test
    public void saveTest() throws Exception{
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("liuqs");
        orderMaster.setBuyerPhone("17628075835");
        orderMaster.setBuyerAddress("四川省成都市");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = orderMasterDao.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenidTest() throws Exception {
        PageRequest pageRequest = new PageRequest(1, 2);
        Page<OrderMaster> result = orderMasterDao.findByBuyerOpenid(OPENID, pageRequest);
        Assert.assertNotEquals(0,result.getTotalElements());
    }

}