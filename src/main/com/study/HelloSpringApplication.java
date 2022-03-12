package com.study;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author admin
 * @version 1.0
 * @description: HelloSpringApplication启动类
 * @date: 2019/5/12
 */
@Api(tags = "HelloSpringApplication启动类")
@RestController
@SpringBootApplication
public class HelloSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringApplication.class, args);
    }

    @ApiOperation(value = "Hello接口")
    @ApiImplicitParam(name = "暂无", value = "暂无", dataType = "String", paramType = "query")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功", response = String.class)})
    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring";
    }

}
