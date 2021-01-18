package com.imooc.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author liuzike
 * @Date 1/6/21
 **/
@SpringBootApplication
@EnableZuulProxy
public class MainProvider {
    public static void main(String[] args) {
        SpringApplication.run(MainProvider.class,args);
    }
}