package com.liuqs.sell.dao;

import com.liuqs.sell.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description:订单详情 Dao 接口
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
public interface OrderDetailDao extends JpaRepository<OrderDetail,String>{

    /**
     * 根据OrderId查询订单详情
     * @param OrderId
     * @return
     */
    List<OrderDetail> findByOrderId(String OrderId);

}
