package com.lihainuo.jwt.service;

import com.lihainuo.common.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 从数据库中查询
        com.lihainuo.common.entity.User user = userMapper.findByUsername(username);

        // 判断用户是否存在
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        // authorities 用于指定角色，这里写死为 ADMIN 管理员
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("ADMIN")
                .build();
    }
//    // 暂时先写死，密码为 lihainuo, 这里填写的密文，数据库中也是存储此种格式
//    // authorities 用于指定角色，这里写死为 ADMIN 管理员
//        return User.withUsername("lihainuo")
//                .
//
//    password("$2a$10$0E4OqoIdPc4FSh/d17cXq.Z5ZoPSZyq/x.0YfAe5TIDyStzDsOHDa")
//                .
//
//    authorities("ADMIN")
//                .
//
//    build();
}

