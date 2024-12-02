package com.lihainuo.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lihainuo.common.dto.UserDTO;
import com.lihainuo.common.entity.User;

/**
 * @author lihainuo
 * @description: 找回服务实现类
 */
public interface RecoverService extends IService<User> {

    void recover(UserDTO userDTO);
}