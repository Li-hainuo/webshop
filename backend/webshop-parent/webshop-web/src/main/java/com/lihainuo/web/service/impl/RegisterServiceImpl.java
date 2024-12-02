package com.lihainuo.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lihainuo.common.dto.UserDTO;
import com.lihainuo.common.entity.User;
import com.lihainuo.common.enums.RespEnum;
import com.lihainuo.common.exception.BusinessException;
import com.lihainuo.common.mapper.UserMapper;
import com.lihainuo.web.service.RegisterService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
/**
 * @author lihainuo
 * @description: 注册服务实现类
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<UserMapper, User> implements RegisterService {
    @Override
    public void register(UserDTO userDTO) {
        User user = new User();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        if (this.getOne(queryWrapper) != null) {
            throw new BusinessException(RespEnum.REGISTER_FAIL_BY_USERNAME);
        }

        user.setUsername(userDTO.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(userDTO.getPassword()));

        this.save(user);
    }
}
