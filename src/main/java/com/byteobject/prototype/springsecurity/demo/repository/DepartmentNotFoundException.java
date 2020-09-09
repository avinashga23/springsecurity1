package com.byteobject.prototype.springsecurity.demo.repository;

import java.util.List;

public class DepartmentNotFoundException extends BaseException {
    public DepartmentNotFoundException(String errorCode, List<Object> args) {
        super(errorCode, args);
    }
}
