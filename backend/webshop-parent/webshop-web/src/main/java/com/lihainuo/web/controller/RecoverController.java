package com.lihainuo.web.controller;

import com.lihainuo.common.Response;
import com.lihainuo.common.dto.UserDTO;
import com.lihainuo.web.service.RecoverService;
import com.lihainuo.web.service.RegisterService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recover")
public class RecoverController {
    @Resource
    private RecoverService recoverService;

    @PostMapping("")
    public Response<Object> recover(@RequestBody UserDTO userDTO) {
        recoverService.recover(userDTO);
        return Response.success();
    }
}
