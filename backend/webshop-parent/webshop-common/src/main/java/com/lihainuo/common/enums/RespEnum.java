package com.lihainuo.common.enums;

import lombok.Getter;

@Getter
public enum RespEnum {
    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR(10000, "出错啦，后台小哥正在努力修复中..."),
    // ----------- 业务异常状态码 -----------
    PRODUCT_NOT_FOUND(20000, "该产品不存在（测试使用）"),
    LOGIN_FAIL(20000, "登录失败"),
    USERNAME_OR_PWD_ERROR(20001, "用户名或密码错误"),
    UNAUTHORIZED(20002, "无访问权限，请先登录！")
    ;

    // 异常码
    private final Integer errorCode;
    // 错误信息
    private final String errorMessage;

    RespEnum(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
