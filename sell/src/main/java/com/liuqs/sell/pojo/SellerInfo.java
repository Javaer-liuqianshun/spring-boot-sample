package com.liuqs.sell.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-26
 * @ Modified:
 **/
@Entity
@Data
public class SellerInfo {
    @Id
    private String id;

    private String username;

    private String password;

    private String openid;
}
