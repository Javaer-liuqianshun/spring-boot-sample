package com.liuqs.springbootwithdruid.entity;

import javax.persistence.*;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-03-07
 * @ Modified:
 **/
@Entity
@Table(name = "zy_admin")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "admin_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "realname")
    private String realname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
