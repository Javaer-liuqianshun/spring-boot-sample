package com.liuqs.sell.service.impl;

import com.liuqs.sell.dao.OrderDetailDao;
import com.liuqs.sell.dao.OrderMasterDao;
import com.liuqs.sell.enums.ResultEnum;
import com.liuqs.sell.exception.SellException;
import com.liuqs.sell.pojo.DTO.CartDTO;
import com.liuqs.sell.pojo.DTO.OrderDTO;
import com.liuqs.sell.pojo.OrderDetail;
import com.liuqs.sell.pojo.OrderMaster;
import com.liuqs.sell.pojo.ProductInfo;
import com.liuqs.sell.service.OrderService;
import com.liuqs.sell.service.ProductService;
import com.liuqs.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description: 订单 Service 实现类
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        // 总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        // 订单id
        String orderId = KeyUtil.getUniqueKey();
        // 购物车
        List<CartDTO> cartDTOList = new ArrayList<>();

        // 1.查询商品(数量,价格)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            // 获取订单明细商品id(product_id)
            String productId = orderDetail.getProductId();
            // 通过商品id查询该商品信息
            ProductInfo productInfo = productService.findOne(productId);
            if (null == productInfo){
                throw new SellException(ResultEnum.PRODUCT_NO_EXIST);
            }
            // 2.计算总价
            orderAmount = orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            // 3.写入订单数据库(orderMaster和orderDetail)
            // 3.1.写入orderDetail
            orderDetail.setOrderId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailDao.save(orderDetail);

            // 4.获取购物车每件商品的productId和productQuantity
            CartDTO cartDTO = new CartDTO(productId,orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }

        // 3.2.写入orderMaster
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);

        // 4.1.扣库存
        // TODO 扣库存


        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public List<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
