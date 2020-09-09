package com.byteobject.prototype.springsecurity.demo.repository;

import java.util.List;

public class BaseException extends RuntimeException {

    private String errorCode;

    private List<Object> args;

    public BaseException(String errorCode, List<Object> args) {
        this.errorCode = errorCode;
        this.args = args;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public List<Object> getArgs() {
        return args;
    }
}
