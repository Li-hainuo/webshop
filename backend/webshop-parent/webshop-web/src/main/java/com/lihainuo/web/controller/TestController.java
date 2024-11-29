package com.lihainuo.web.controller;

import com.lihainuo.common.aspect.ApiOperationLog;
import com.lihainuo.common.enums.RespEnum;
import com.lihainuo.common.exception.BusinessException;
import com.lihainuo.web.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public User test(@RequestBody User user) {
        throw new BusinessException(RespEnum.PRODUCT_NOT_FOUND);
    }

}