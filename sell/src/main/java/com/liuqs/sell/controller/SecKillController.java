package com.liuqs.sell.controller;

import com.liuqs.sell.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-28
 * @ Modified:
 **/
@RestController
@RequestMapping(value = "/skill")
@Slf4j
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    /**
     * 查询秒杀活动特价商品的信息
     * @param productId
     * @return
     */
    @GetMapping(value = "/query/{productId}")
    public String query(@PathVariable("productId")String productId){
        return secKillService.querySecKillProductInfo(productId);
    }

    /**
     * 秒杀,没有抢到获得 '哎呦喂,xxxxx',抢到了会返回剩余的库存量
     * @param productId
     * @return
     */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable("productId")String productId){
      log.info("skill request,productId:"+productId);
      secKillService.orderProductMockDiffUser(productId);
      return secKillService.querySecKillProductInfo(productId);
    }
}
