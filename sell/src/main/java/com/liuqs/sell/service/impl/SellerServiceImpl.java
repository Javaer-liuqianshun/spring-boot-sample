package com.liuqs.sell.service.impl;

import com.liuqs.sell.enums.ResultEnum;
import com.liuqs.sell.pojo.ProductCategory;
import com.liuqs.sell.pojo.ProductInfo;
import com.liuqs.sell.pojo.VO.CategoryFormVO;
import com.liuqs.sell.service.CategoryService;
import com.liuqs.sell.service.ProductService;
import com.liuqs.sell.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-01-11
 * @ Modified:
 **/
@Service
@Slf4j
public class SellerServiceImpl implements SellerService {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public int saveOrUpdateCategory(CategoryFormVO form) {
        int result = 0;
        try {
            Integer categoryId = form.getCategoryId();
            ProductCategory productCategory = new ProductCategory();
            if (null != categoryId) {
                // 修改,先查询该类目下是否有商品
                productCategory = categoryService.findOne(categoryId);
                List<ProductInfo> productInfoList = productService.findByCategoryType(productCategory.getCategoryType());
                if (null != productInfoList && productInfoList.size()>0) {
                    log.info("【卖家端类目】该类目下有商品信息,不能更改type");
                    return ResultEnum.CATEGORY_HAVE_PRODUCT.getCode();
                }
            }
            // 新增和修改 共同操作-->判断更改的categoryType值是否已经在表中存在
            Integer categoryType = form.getCategoryType();
            ArrayList<Integer> categoryTypeList = new ArrayList<>();
            categoryTypeList.add(categoryType);
            List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
            // 如果是修改,需要移除自身productCategory对象
            for (ProductCategory p: productCategoryList){
                Integer c = p.getCategoryType();
                if (c == categoryType){
                    productCategoryList.remove(p);
                }
            }
            if (null != productCategoryList && productCategoryList.size() > 0) {
                log.info("【卖家端类目】已存在该type");
                result = ResultEnum.GATEGORY_TYPE_IS_EXIST.getCode();
            }
            BeanUtils.copyProperties(form, productCategory);
            categoryService.save(productCategory);
        } catch (Exception e) {
            log.error("【卖家端类目】新增或编辑操作异常");
            result = ResultEnum.CATEGORY_EDIT_FIAL.getCode();
        }
        return result;
    }

}
