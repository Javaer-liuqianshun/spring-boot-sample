package com.liuqs.sell.dao;

import com.liuqs.sell.pojo.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-26
 * @ Modified:
 **/
public interface SellerInfoDao extends JpaRepository<SellerInfo, String> {

    /**
     * 通过openId查找用户信息
     *
     * @param openid
     */
    SellerInfo findByOpenid(String openid);

}
