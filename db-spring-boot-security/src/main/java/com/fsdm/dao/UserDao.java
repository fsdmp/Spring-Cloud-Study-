package com.fsdm.dao;


import com.fsdm.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Dao 继承了 JpaRepository, JpaRepository 默认实现了大多数单表查询的操作。
 * UserDao 中自定义 个根据 usemame 获取 User 的方法，由于 JPA 已经自动实现了根据 usemame
 * 字段去查找用户 的方法，因此不需要额外的工作
 */
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
