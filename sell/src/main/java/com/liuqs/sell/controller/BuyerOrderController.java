package com.liuqs.sell.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liuqs.sell.enums.ResultEnum;
import com.liuqs.sell.exception.SellException;
import com.liuqs.sell.pojo.DTO.OrderDTO;
import com.liuqs.sell.pojo.OrderDetail;
import com.liuqs.sell.pojo.VO.OrderFormVO;
import com.liuqs.sell.pojo.VO.ResultVO;
import com.liuqs.sell.service.BuyerService;
import com.liuqs.sell.service.OrderService;
import com.liuqs.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author: liuqianshun
 * @ Description: 买家订单
 * @ Date: Created in 2017-12-20
 * @ Modified:
 **/
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     *
     * @param orderFormVO
     * @param bindingResult 校验信息返回到BindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderFormVO orderFormVO, BindingResult bindingResult) {

        Gson gson = new Gson();

        // 1.订单参数检查
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确,orderFormVO={}", orderFormVO);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        // 2.把订单数据转成orderDTO
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderFormVO.getName());
        orderDTO.setBuyerPhone(orderFormVO.getPhone());
        orderDTO.setBuyerAddress(orderFormVO.getAddress());
        orderDTO.setBuyerOpenid(orderFormVO.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            // 把json格式的字符串转成List
            orderDetailList = gson.fromJson(orderFormVO.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误,String={}", orderFormVO.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能不空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        // 3.创建订单
        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    /**
     * 根据openid分页查询订单列表
     *
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        // 1.openid参数检查
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        // 2.据openid分页查询订单列表
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);

        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    /**
     * 根据orderId查询订单详情
     *
     * @param openid
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOneOrder(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    /**
     * 取消订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }
}
