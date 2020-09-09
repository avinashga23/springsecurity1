package com.byteobject.prototype.springsecurity.demo.repository;

import java.util.List;

public class PersonNotFoundException extends BaseException {
    public PersonNotFoundException(String errorCode, List<Object> args) {
        super(errorCode, args);
    }
}
