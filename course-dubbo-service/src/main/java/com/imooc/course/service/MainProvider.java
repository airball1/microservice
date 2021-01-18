package com.imooc.course.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author liuzike
 * @Date 1/6/21
 **/
@SpringBootApplication
@MapperScan("com.imooc.course.service")
public class MainProvider {
    public static void main(String[] args) {
        SpringApplication.run(MainProvider.class,args);
    }
}