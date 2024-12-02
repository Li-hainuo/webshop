package com.lihainuo.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lihainuo.common.dto.UserDTO;
import com.lihainuo.common.entity.User;
/**
 * @author lihainuo
 * @description: 注册服务实现类
 */
public interface RegisterService extends IService<User> {
    void register(UserDTO userDTO);
}
