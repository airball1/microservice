package com.imooc.useredge.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.immoc.service.MessageService;
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

    @Value("${spring.dubbo.registry.address}")
    private String registryAddress;

    @Value("${message.service.ip}")
    private String messageServiceIp;

    @Bean
    public UserService userServiceReferenceConfig() {
        ReferenceConfig<UserService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig(applicationName));
        reference.setRegistry(new RegistryConfig(registryAddress));
        reference.setInterface(UserService.class);
        reference.setCheck(false);
        reference.setUrl("dubbo://" + userServiceIp + ":7911");

        return reference.get();
    }

    @Bean
    public MessageService MessageServiceReferenceConfig() {
        ReferenceConfig<MessageService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig(applicationName));
        reference.setRegistry(new RegistryConfig(registryAddress));
        reference.setInterface(MessageService.class);
        reference.setCheck(false);
        reference.setUrl("dubbo://" + messageServiceIp + ":9090");

        return reference.get();
    }

}
