package com.watsoo.borrow_service.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.watsoo.borrow_service.feignclient")
public class FeignClientConfig {

}
