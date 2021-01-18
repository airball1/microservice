package com.imooc.course.service.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.imooc.userservice.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author liuzike
 * @Date 1/10/21
 **/
@Configuration
public class DubboConfig {

    @Value("${user.service.ip}")
    private String userServiceIp;

    @Value("${spring.dubbo.application.name}")
    private String applicationName;

    @Bean
    public UserService userServiceReferenceConfig() {
        ReferenceConfig<UserService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig(applicationName));
        reference.setInterface(UserService.class);
        reference.setCheck(false);
        reference.setUrl("dubbo://" + userServiceIp + ":7911");

        return reference.get();
    }
}
