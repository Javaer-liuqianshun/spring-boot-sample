package com.liuqs.sell.service;

import com.liuqs.sell.pojo.SellerInfo;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-26
 * @ Modified:
 **/
public interface SellerInfoService {

    /**
     * 根据openid查询 用户信息
     *
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
