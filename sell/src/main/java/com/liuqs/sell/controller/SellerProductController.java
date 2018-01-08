package com.liuqs.sell.controller;

import com.liuqs.sell.enums.ResultEnum;
import com.liuqs.sell.exception.SellException;
import com.liuqs.sell.pojo.ProductCategory;
import com.liuqs.sell.pojo.ProductInfo;
import com.liuqs.sell.pojo.VO.ProductFormVO;
import com.liuqs.sell.service.CategoryService;
import com.liuqs.sell.service.ProductService;
import com.liuqs.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
 * @ Description:卖家端商品管理
 * @ Date: Created in 2018-01-07
 * @ Modified:
 **/
@Controller
@RequestMapping(value = "/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 商品列表
     * @param page
     * @param size
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/list")
    public String list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                       @RequestParam(value = "size",defaultValue = "10") Integer size,
                       ModelMap modelMap){
        PageRequest pageRequest = new PageRequest(page - 1, size);
        // 分页查询商品信息
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        modelMap.addAttribute("productInfoPage",productInfoPage);
        // 当前页
        modelMap.addAttribute("currentPage",page);
        // 每页显示数
        modelMap.addAttribute("size",size);
        return "product/list";
    }

    /**
     * 商品下架
     * @param productId
     * @param modelMap
     * @return
     */
    @GetMapping(value ="/off_sale")
    public String offSale(@RequestParam(value = "productId") String productId,
                          ModelMap modelMap){
        try {
            productService.offSale(productId);
        }catch (SellException e){
            // 异常处理
            log.error("【卖家端商品下架】发生异常{}",e);
            modelMap.addAttribute("msg",e.getMessage());
            modelMap.addAttribute("url","/sell/seller/product/list");
            return "common/error";
        }
        modelMap.addAttribute("msg", ResultEnum.PRODUCT_STATUS_DOWN.getMessage());
        modelMap.addAttribute("url","/sell/seller/product/list");
        return "common/success";
    }

    /**
     * 商品上架
     * @param productId
     * @param modelMap
     * @return
     */
    @GetMapping(value="/on_sale")
    public String onSale(@RequestParam(value = "productId") String productId,
                         ModelMap modelMap){
        try {
            productService.onSale(productId);
        }catch (SellException e){
            // 异常处理
            log.error("【卖家端商品上架】发生异常{}",e);
            modelMap.addAttribute("msg",e.getMessage());
            modelMap.addAttribute("url","/sell/seller/product/list");
            return "common/error";
        }
        modelMap.addAttribute("msg", ResultEnum.PRODUCT_STATUS_UP.getMessage());
        modelMap.addAttribute("url","/sell/seller/product/list");
        return "common/success";
    }

    /**
     * 新增和修改 页面
     * @param productId
     * @param modelMap
     * @return
     */
    @GetMapping(value ="/toEdit")
    public String toEdit(@RequestParam(value = "productId",required = false) String productId,
                       ModelMap modelMap){
        // 1.如果productId不为空,为编辑,需要查询出该商品信息
        if (StringUtils.isNotEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            modelMap.addAttribute("productInfo",productInfo);
        }
        // 2.查询所有类目
        List<ProductCategory> productCategoryList = categoryService.findAll();
        modelMap.addAttribute("productCategoryList",productCategoryList);

        return "product/edit";
    }

    /**
     * 新增和修改 操作
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/edit")
    public String edit(@Valid ProductFormVO form,
                       BindingResult bindingResult,ModelMap modelMap){
        // 1.商品参数检查
        if (bindingResult.hasErrors()){
            log.error("【卖家端商品】参数校验错误{}",bindingResult.getFieldError().getDefaultMessage());
            modelMap.addAttribute("msg",bindingResult.getFieldError().getDefaultMessage());
            modelMap.addAttribute("url","/sell/seller/product/list");
            return "common/error";
        }
        try{
            ProductInfo productInfo = new ProductInfo();
            // 2.新增和修改操作,如果productId不存在,则为新增
            if (StringUtils.isNotEmpty(form.getProductId())){
                productInfo = productService.findOne(form.getProductId());
            }else{
                form.setProductId(KeyUtil.getUniqueKey());
            }
            // 3.拷贝对象
            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        }catch (Exception e){
            log.error("【卖家端商品】新增或编辑操作异常");
            modelMap.addAttribute("msg","新增或编辑操作异常");
            modelMap.addAttribute("url","/sell/seller/product/list");
            return "common/error";
        }
        modelMap.addAttribute("url","/sell/seller/product/list");
        return "common/success";
    }

}
