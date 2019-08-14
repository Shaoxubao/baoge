package com.baoge.springbootweb.controller;

import com.baoge.springbootweb.exception.UserNotException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    // 浏览器客户端返回的都是json
//    @ResponseBody
//    @ExceptionHandler(UserNotException.class)
//    public Map<String, Object> handleException(Exception e){
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user.notexist");
//        map.put("message", e.getMessage());
//
//        return map;
//    }

    // 浏览器客户端返回的都是json
    @ExceptionHandler(UserNotException.class)
    public String handleException(Exception e, HttpServletRequest request){

        Map<String, Object> map = new HashMap<>();
        // 传入我们自己的错误状态码
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "user.notexist");
        map.put("message", "用户出错了");

        request.setAttribute("ext", map);

        return "forward:/error";
    }
}
