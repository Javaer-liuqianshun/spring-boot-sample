package com.liuqs.sell.service.impl;

import com.liuqs.sell.dao.SellerInfoDao;
import com.liuqs.sell.pojo.SellerInfo;
import com.liuqs.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-26
 * @ Modified:
 **/
@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }
}
