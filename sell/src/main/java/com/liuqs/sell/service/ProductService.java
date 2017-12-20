package com.liuqs.sell.service;

import com.liuqs.sell.pojo.DTO.CartDTO;
import com.liuqs.sell.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description: 商品 Service 接口
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
public interface ProductService {

    /**
     * 查询单个商品信息
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 保存单个商品信息
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 减库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);
}
