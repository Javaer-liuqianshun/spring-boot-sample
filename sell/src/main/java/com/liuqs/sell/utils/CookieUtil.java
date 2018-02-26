package com.liuqs.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-26
 * @ Modified:
 **/
public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }


    /**
     * 获取cookie
     * @param request
     * @param name
     */
    public static Cookie get(HttpServletRequest request,String name){
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }
        return null;
    }

    /**
     * 将cookie封装成Map
     * @param request
     * @return
     */
    private static Map<String,Cookie> readCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap  = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (null!=cookies){
            for(Cookie cookie:cookies){
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
