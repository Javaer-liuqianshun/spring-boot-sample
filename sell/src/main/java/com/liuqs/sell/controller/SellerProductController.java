package com.liuqs.sell.controller;

import com.liuqs.sell.enums.ResultEnum;
import com.liuqs.sell.exception.SellException;
import com.liuqs.sell.pojo.ProductInfo;
import com.liuqs.sell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
