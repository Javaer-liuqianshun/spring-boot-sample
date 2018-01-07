package com.liuqs.sell.utils;

import com.liuqs.sell.enums.CodeEnum;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-01-07
 * @ Modified:
 **/
public class EnumUtil {

    /**
     * 根据code获取枚举实例
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends CodeEnum> T getEnumByCode(Integer code, Class<T> enumClass) {
        // getEnumConstants() 返回枚举类的元素,如果该Class不是枚举类型,则返回null
        T[] enumConstants = enumClass.getEnumConstants();
        if (null != enumConstants) {
            for (T enumInstance : enumConstants) {
                if (code.equals(enumInstance.getCode())){
                    return enumInstance;
                }
            }
        }
        return null;
    }


}
