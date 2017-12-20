package com.liuqs.sell.utils;

import com.liuqs.sell.pojo.VO.ResultVO;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
