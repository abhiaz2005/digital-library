package com.watsoo.catalog_service.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.watsoo.catalog_service.dto.Response;
import com.watsoo.catalog_service.dto.UserDto;
import com.watsoo.catalog_service.feignClient.fallback.BorrowFeignClientFallback;

@FeignClient(name = "borrow-service",fallback = BorrowFeignClientFallback.class)
public interface BorrowFeignClient {

	@GetMapping("get/user")
	public Response<UserDto> getUserById(@RequestParam(required = false) Long id);
	
	
}
