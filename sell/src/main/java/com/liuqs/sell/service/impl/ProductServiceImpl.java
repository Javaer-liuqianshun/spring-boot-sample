package com.liuqs.sell.service.impl;

import com.liuqs.sell.dao.ProductInfoDao;
import com.liuqs.sell.enums.ProductStatusEnum;
import com.liuqs.sell.enums.ResultEnum;
import com.liuqs.sell.exception.SellException;
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
            // 1.先根据productId获取商品信息
            ProductInfo productInfo = productInfoDao.findOne(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NO_EXIST);
            }
            // 2.商品库存 - 购物车数量
            int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            // 3.更新库存
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }
    }

    @Transactional
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            // 1.先根据productId获取商品信息
            ProductInfo productInfo = productInfoDao.findOne(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NO_EXIST);
            }
            // 2.更新库存
            int result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }
    }
}
