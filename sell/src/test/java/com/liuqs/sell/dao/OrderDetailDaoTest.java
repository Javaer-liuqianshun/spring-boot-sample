package com.liuqs.sell.dao;

import com.liuqs.sell.pojo.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    private final String ORDERID = "123456";

    @Test
    public void saveTest() throws Exception {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567800");
        orderDetail.setOrderId(ORDERID);
        orderDetail.setProductId("123456");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(1.2));
        orderDetail.setProductQuantity(2);
        orderDetail.setProductIcon("http://www.jpg");
        OrderDetail result = orderDetailDao.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderIdTest() throws Exception {
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(ORDERID);
        System.out.println(orderDetailList);
    }

}