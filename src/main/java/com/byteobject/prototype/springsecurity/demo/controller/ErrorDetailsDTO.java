package com.byteobject.prototype.springsecurity.demo.controller;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetailsDTO {

    private String errorCode;

    private Date timeStamp;

    private String message;

}
