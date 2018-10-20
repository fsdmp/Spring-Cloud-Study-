package com.fsdm.pojo;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Role 类实现了 GrantedAuthority 接口，并重写了其 getAuthority 方法。权限点可以为任
 * 何字符串，不 定是角色名 字符串 关键是 getAuthority 方法如何实现。本例的权限点是从
 * 数据库读取 Role表的 name 字段。
 */
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
