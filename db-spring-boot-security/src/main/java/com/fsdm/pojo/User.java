package com.fsdm.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * User 类实现了 UserDetails 接口，该接口是实现 Spring Security 认证信息的核心接
 * 口。其中， getUsemame 方法为 serDetails 接口的方法，这个方法不 定返回 usemame ，也可
 * 是其他的用户信息，例如 码、邮箱地址等。 getAuthorities( ）方法返回 是该用户设置
 * 的权限信息 在本例中，从数据库中读取该用户的所有角色信息，权限信息也可以是用户的其
 * 他信息，不一定是角色信息。另外需要读取密码，最后几个方法一般情况下都返回 true ，也可
 * 以根据自己的需求进行业务判断
 */
@Entity
public class User implements UserDetails, Serializable {

    @Id
    //主键生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String password;
    /**
     * 多对多
     * fetch：定义从数据库中获取数据的策略
     * cascade：定义传播行为
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //关联数据库表
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> authorities;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
