package com.liuqs.springbootfastjson.repository;

import com.liuqs.springbootfastjson.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-03-08
 * @ Modified:
 **/
public interface UserRepository extends JpaRepository<UserEntity,Integer>, JpaSpecificationExecutor<UserEntity>{
}
