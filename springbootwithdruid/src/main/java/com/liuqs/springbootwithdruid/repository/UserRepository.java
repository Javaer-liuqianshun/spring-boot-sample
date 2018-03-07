package com.liuqs.springbootwithdruid.repository;

import com.liuqs.springbootwithdruid.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-03-07
 * @ Modified:
 **/
public interface UserRepository extends JpaRepository<UserEntity,Long>,JpaSpecificationExecutor<UserEntity> {



}
