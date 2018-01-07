package com.liuqs.sell.controller;

import com.liuqs.sell.enums.ResultEnum;
import com.liuqs.sell.exception.SellException;
import com.liuqs.sell.pojo.DTO.OrderDTO;
import com.liuqs.sell.service.OrderService;
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
 * @ Description: 卖家端订单
 * @ Date: Created in 2017-12-29
 * @ Modified:
 **/
@Controller
@RequestMapping(value = "/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单展示
     * @param page
     * @param size
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       ModelMap modelMap) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        modelMap.addAttribute("orderDTOPage",orderDTOPage);
        // 当前页
        modelMap.addAttribute("currentPage",page);
        // 每页显示条数
        modelMap.addAttribute("size",size);
        return "order/list";
    }

    /**
     * 取消订单
     * @param orderId
     * @param modelMap
     * @return
     */
    @GetMapping(value ="/cancel")
    public String cancel(@RequestParam(value = "orderId") String orderId,
                         ModelMap modelMap){
        try {
            // 根据订单查询订单
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】发生异常{}",e);
            modelMap.addAttribute("msg",e.getMessage());
            modelMap.addAttribute("url","/sell/seller/order/list");
            return "common/error";
        }

        modelMap.addAttribute("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        modelMap.addAttribute("url","/sell/seller/order/list");
        return "common/success";
    }

    /**
     * 订单详情
     * @param orderId
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/detail")
    public String detail(@RequestParam(value = "orderId") String orderId,
                         ModelMap modelMap){
        OrderDTO orderDTO;
        try {
            // 根据订单查询订单
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("【卖家端查看订单详情】发生异常{}",e);
            modelMap.addAttribute("msg",e.getMessage());
            modelMap.addAttribute("url","/sell/seller/order/list");
            return "common/error";
        }
        modelMap.addAttribute("orderDTO", orderDTO);
        return "order/detail";
    }

    /**
     * 完结订单
     * @param orderId
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/finish")
    public String finished(@RequestParam(value = "orderId") String orderId,
                           ModelMap modelMap){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端完结订单】发生异常{}", e);
            modelMap.put("msg", e.getMessage());
            modelMap.put("url", "/sell/seller/order/list");
            return "common/error";
        }

        modelMap.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        modelMap.put("url", "/sell/seller/order/list");
        return "common/success";
    }
}
