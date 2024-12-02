package com.lihainuo.web.controller;

import com.lihainuo.common.Response;
import com.lihainuo.common.dto.UserDTO;
import com.lihainuo.web.service.RegisterService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Resource
    private RegisterService registerService;

    @PostMapping("")
    public Response<Object> register(@RequestBody UserDTO userDTO) {
        registerService.register(userDTO);
        return Response.success();
    }
}
