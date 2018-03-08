package com.liuqs.springbootfastjson.entity;

import javax.persistence.*;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-03-08
 * @ Modified:
 **/
@Table(name = "t_user")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "t_id")
    private Integer id;

    @Column(name = "t_name")
    private String name;

    @Column(name = "t_age")
    private Integer age;

    @Column(name = "t_address")
    private Integer address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }
}
