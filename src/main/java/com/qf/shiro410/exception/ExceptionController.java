package com.qf.shiro410.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice//标识此为控制层的通知（aop）
public class ExceptionController {

    @ExceptionHandler(value = UnauthorizedException.class)
    public String dealException(HttpServletRequest request,Exception ex){
        return "unauth";
    }
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public String dealException2(HttpServletRequest request,Exception ex){
        return "unauth";
    }
}
