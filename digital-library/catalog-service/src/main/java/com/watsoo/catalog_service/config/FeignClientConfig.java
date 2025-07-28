package com.watsoo.catalog_service.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.watsoo.catalog_service.feignClient")
public class FeignClientConfig {

}
