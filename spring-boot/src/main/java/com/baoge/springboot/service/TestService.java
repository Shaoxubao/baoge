package com.baoge.springboot.service;


import com.baoge.springboot.common.ServerResponse;

public interface TestService {

    ServerResponse testIdempotence();

    ServerResponse accessLimit();

}
