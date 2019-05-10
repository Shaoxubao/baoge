package com.baoge.springboot.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shaoxubao
 * @Date 2019/5/10 11:09
 */

@Api(description = "用户接口")
@RestController
@RequestMapping("/hello")
public class SwaggerHelloController {

    @ApiOperation(value = "新增用户" ,  notes="新增注册")
    @RequestMapping(value="/user",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResObject createUser(@RequestBody User user) {
        System.out.println("createUser:::" + user.toString());
        return new ResObject(HttpStatus.OK.value(), "新增成功.");
    }

    @ApiOperation(value = "查询用户" ,  notes="查询用户")
    @RequestMapping(value="/users",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResObject getUser(User user) {
        System.out.println("getUser:::" + user.toString());
        return new ResObject(HttpStatus.OK.value(), "查询成功.");
    }
}
