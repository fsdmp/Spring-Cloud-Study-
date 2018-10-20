package com.fsdm.service.impl;

import com.fsdm.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service 层需要实现 UserDetails Service 接口，该接口是根据用户名获取该用户的所有信息，
 * 包括用户信息和权限点，
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userRepository;

    /**
     * 此方法底层会给密码字段加密
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
