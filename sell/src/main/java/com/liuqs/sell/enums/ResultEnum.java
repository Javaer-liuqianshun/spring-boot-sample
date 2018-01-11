package com.liuqs.sell.enums;


import lombok.Getter;

/**
 * @ Author: liuqianshun
 * @ Description: 返回信息
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Getter
public enum ResultEnum implements CodeEnum{

    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NO_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_DETAIL_EMPTY(16, "订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),
    CART_EMPTY(18, "购物车为空"),
    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),
    WECHAT_MP_ERROR(20, "微信公众账号方面错误"),
    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),
    ORDER_FINISH_SUCCESS(23, "订单完结成功"),
    PRODUCT_STATUS_ERROR(24, "商品状态不正确"),
    PRODUCT_STATUS_DOWN(25, "商品下架成功"),
    PRODUCT_STATUS_UP(26, "商品上架成功"),
    CATEGORY_HAVE_PRODUCT(27,"该类目下存在商品"),
    GATEGORY_TYPE_IS_EXIST(28,"该type已经存在"),
    CATEGORY_EDIT_FIAL(29,"类目信息编辑失败"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
