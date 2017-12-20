package com.liuqs.sell.service.impl;

import com.liuqs.sell.dao.ProductInfoDao;
import com.liuqs.sell.enums.ProductStatusEnum;
import com.liuqs.sell.pojo.DTO.CartDTO;
import com.liuqs.sell.pojo.ProductInfo;
import com.liuqs.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description: 商品 Service 实现类
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Transactional
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO :cartDTOList){
            // 先根据productId获取商品信息
            productInfoDao.findOne();
        }
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }
}
