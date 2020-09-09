package com.byteobject.prototype.springsecurity.demo.controller;

import com.byteobject.prototype.springsecurity.demo.repository.DepartmentNotFoundException;
import com.byteobject.prototype.springsecurity.demo.repository.PersonNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class Advice {

    private MessageSource messageSource;

    public Advice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDetailsDTO handlePersonNotFoundException(PersonNotFoundException e) {
        ErrorDetailsDTO errorDetailsDTO = getErrorDetails(e.getErrorCode(), e.getArgs());

        return errorDetailsDTO;
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDetailsDTO handleDepartmentNotFound(DepartmentNotFoundException e) {
        ErrorDetailsDTO errorDetailsDTO = getErrorDetails(e.getErrorCode(), e.getArgs());

        return errorDetailsDTO;
    }

    @NotNull
    private ErrorDetailsDTO getErrorDetails(String errorCode, List<Object> args) {
        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO();
        errorDetailsDTO.setErrorCode(errorCode);
        errorDetailsDTO.setTimeStamp(new Date());
        errorDetailsDTO.setMessage(messageSource.getMessage(errorCode, args.toArray(), Locale.getDefault()));

        return errorDetailsDTO;
    }

}
