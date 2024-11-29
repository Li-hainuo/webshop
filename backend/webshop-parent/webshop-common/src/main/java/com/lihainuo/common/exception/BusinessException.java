package com.lihainuo.common.exception;

import com.lihainuo.common.enums.RespEnum;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private RespEnum respEnum;

    public BusinessException() {

    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(RespEnum respEnum) {
        super(respEnum.getErrorMessage());
        this.respEnum = respEnum;
    }

}

