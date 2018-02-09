package com.liuqs.springbootweb.service;

import com.liuqs.springbootweb.enums.ResultEnum;
import com.liuqs.springbootweb.exception.WebException;
import org.springframework.stereotype.Service;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-09
 * @ Modified:
 **/
@Service
public class GirlService {

    public void getAge(int age) throws Exception {
        if (age < 10) {
            // 返回"你还在上小学吧" code=100
            throw new WebException(ResultEnum.PRIMARY_SCHOOL);
        } else if(age > 10 && age < 16) {
            // 返回"你可能在上初中吧" code=101
            throw new WebException(ResultEnum.MIDDLE_SCHOOL);
        }

    }

}
