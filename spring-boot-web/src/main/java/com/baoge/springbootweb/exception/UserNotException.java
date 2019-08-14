package com.baoge.springbootweb.exception;

public class UserNotException extends RuntimeException {

    public UserNotException(){
        super("用户不存在");
    }
}
