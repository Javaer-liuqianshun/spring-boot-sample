package com.liuqs.sell.service;

import com.liuqs.sell.pojo.VO.CategoryFormVO;

/**
 * @ Author: liuqianshun
 * @ Description: 卖家端 service 接口
 * @ Date: Created in 2018-01-11
 * @ Modified:
 **/
public interface SellerService {

    /**
     * 新增和修改
     *
     * 修改: 此时categoryId不为空,更新时,如果更改categoryType字段,
     *          需要检查该类目下是否有对应的商品信息,如果有,不能更新
     *       不如没有,还需检查更改的categoryType值是否已经在表中存在
     *
     * 新增: 需检查更改的categoryType值是否已经在表中存在
     *
     *
     * @param formVO
     * @return
     */
    int saveOrUpdateCategory(CategoryFormVO formVO);
}
