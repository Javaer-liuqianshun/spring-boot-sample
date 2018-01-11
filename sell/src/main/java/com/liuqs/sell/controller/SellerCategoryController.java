package com.liuqs.sell.controller;

import com.liuqs.sell.enums.ResultEnum;
import com.liuqs.sell.pojo.ProductCategory;
import com.liuqs.sell.pojo.VO.CategoryFormVO;
import com.liuqs.sell.service.CategoryService;
import com.liuqs.sell.service.SellerService;
import com.liuqs.sell.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description:卖家端类目
 * @ Date: Created in 2018-01-11
 * @ Modified:
 **/
@Controller
@RequestMapping(value = "/seller/category")
@Slf4j
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SellerService sellerService;

    /**
     * 类目列表展示
     *
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/list")
    public String list(ModelMap modelMap) {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        modelMap.addAttribute("categoryList", productCategoryList);
        return "category/list";
    }

    /**
     * 新增和修改 页面
     *
     * @param categoryId
     * @return
     */
    @GetMapping(value = "/toEdit")
    public String toEdit(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                         ModelMap modelMap) {
        // 如果categoryId不为空,为编辑,需要查询出类目信息
        if (null != categoryId) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            modelMap.addAttribute("category", productCategory);
        }
        return "category/edit";
    }


    /**
     * 新增和修改 操作
     *
     * @param form
     * @param bindingResult
     * @param modelMap
     * @return
     */
    @PostMapping(value = "/edit")
    public String edit(@Valid CategoryFormVO form, BindingResult bindingResult,
                       ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            log.error("【卖家端类目】参数校验错误{}", bindingResult.getFieldError().getDefaultMessage());
            modelMap.addAttribute("msg", bindingResult.getFieldError().getDefaultMessage());
            modelMap.addAttribute("url", "/sell/seller/category/list");
            return "common/error";
        }
        int result = sellerService.saveOrUpdateCategory(form);
        if (result != 0) {
            modelMap.addAttribute("msg", EnumUtil.getEnumByCode(result, ResultEnum.class).getMessage());
            modelMap.addAttribute("url", "/sell/seller/category/list");
            return "common/error";
        }
        modelMap.addAttribute("url", "/sell/seller/category/list");
        return "common/success";
    }
}
