package com.liuqs.sell.dao;

import com.liuqs.sell.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ Author: liuqianshun
 * @ Description: 订单 Dao 接口
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {

    /**
     * 根据Openid分页查询
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
