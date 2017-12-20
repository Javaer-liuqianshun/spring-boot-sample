package com.liuqs.sell.pojo.VO;

import lombok.Data;

/**
 * @ Author: liuqianshun
 * @ Description: Http请求发返回的最外层对象
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Data
public class ResultVO<T> {

    /** 错误码 */
    private Integer code;
    /** 提示信息 */
    private String msg;
    /** 具体内容 */
    private T data;
}
