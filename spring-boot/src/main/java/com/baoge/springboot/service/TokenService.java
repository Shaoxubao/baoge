package com.baoge.springboot.service;

import com.baoge.springboot.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    ServerResponse createToken();

    void checkToken(HttpServletRequest request);

}
