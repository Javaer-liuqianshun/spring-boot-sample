package com.liuqs.sell.service;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-28
 * @ Modified:
 **/
public interface SecKillService {

    String querySecKillProductInfo(String productId);

    void orderProductMockDiffUser(String productId);
}
