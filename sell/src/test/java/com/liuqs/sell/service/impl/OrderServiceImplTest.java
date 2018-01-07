package com.liuqs.sell.service.impl;

import com.liuqs.sell.enums.OrderStatusEnum;
import com.liuqs.sell.enums.PayStatusEnum;
import com.liuqs.sell.pojo.DTO.OrderDTO;
import com.liuqs.sell.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    // 买家微信openid
    private String BUYER_OPENID = "abc123";
    // 订单id
    private String ORDER_ID = "1513736219600361229";

    /**
     * 创建订单
     *
     * @throws Exception
     */
    @Test
    public void create() throws Exception {
        for (int i = 0; i < 10; i++) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setBuyerName("lhl"+i);
            orderDTO.setBuyerPhone("12345678910");
            orderDTO.setBuyerAddress("测试数据"+i);
            orderDTO.setBuyerOpenid(BUYER_OPENID);

            List<OrderDetail> orderDetailList = new ArrayList<>();
            OrderDetail o1 = new OrderDetail();
            o1.setProductId("123456");
            o1.setProductQuantity(1);

            OrderDetail o2 = new OrderDetail();
            o2.setProductId("1234567");
            o2.setProductQuantity(1);

            orderDetailList.add(o1);
            orderDetailList.add(o2);

            orderDTO.setOrderDetailList(orderDetailList);

            OrderDTO result = orderServiceImpl.create(orderDTO);
            log.info("【创建订单】result={}", result);
            Assert.assertNotNull(result);
        }

    }

    /**
     * 根据order_id查询订单详情
     *
     * @throws Exception
     */
    @Test
    public void findOne() throws Exception {
        OrderDTO result = orderServiceImpl.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}", result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }

    /**
     * 根据buyer_openid分页查询订单信息
     *
     * @throws Exception
     */
    @Test
    public void findListByOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderServiceImpl.findList(BUYER_OPENID, pageRequest);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    /**
     * 分页查询订单信息
     *
     * @throws Exception
     */
    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderServiceImpl.findList(pageRequest);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    /**
     * 根据order_id取消订单
     *
     * @throws Exception
     */
    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderServiceImpl.findOne(ORDER_ID);
        OrderDTO result = orderServiceImpl.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    /**
     * 根据order_id完结订单
     *
     * @throws Exception
     */
    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderServiceImpl.findOne(ORDER_ID);
        OrderDTO result = orderServiceImpl.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    /**
     * 根据order_id支付订单
     *
     * @throws Exception
     */
    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderServiceImpl.findOne(ORDER_ID);
        OrderDTO result = orderServiceImpl.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

}