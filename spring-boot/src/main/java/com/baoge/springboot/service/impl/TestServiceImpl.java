package com.baoge.springboot.service.impl;

import com.baoge.springboot.common.ServerResponse;
import com.baoge.springboot.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }

    @Override
    public ServerResponse accessLimit() {
        return ServerResponse.success("accessLimit: success");
    }

}
