package com.liuqs.sell.controller;

import com.liuqs.sell.pojo.ProductCategory;
import com.liuqs.sell.pojo.ProductInfo;
import com.liuqs.sell.pojo.VO.ProductInfoVO;
import com.liuqs.sell.pojo.VO.ProductVO;
import com.liuqs.sell.pojo.VO.ResultVO;
import com.liuqs.sell.service.CategoryService;
import com.liuqs.sell.service.ProductService;
import com.liuqs.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description:买家商品
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService CategoryService;

    @GetMapping(value = "/list")
    public ResultVO list() {
        // 1.查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 2.查询类目
        List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
        List<ProductCategory> productCategoryList = CategoryService.findByCategoryTypeIn(categoryTypeList);
        // 3.拼装数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList) {
            ProductVO  productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategorytype(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo: productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

}
