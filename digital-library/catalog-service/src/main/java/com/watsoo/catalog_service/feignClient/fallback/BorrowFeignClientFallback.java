package com.watsoo.catalog_service.feignClient.fallback;

import org.springframework.http.HttpStatus;

import com.watsoo.catalog_service.dto.Response;
import com.watsoo.catalog_service.dto.UserDto;
import com.watsoo.catalog_service.feignClient.BorrowFeignClient;

public class BorrowFeignClientFallback implements BorrowFeignClient {

	@Override
	public Response<UserDto> getUserById(Long id) {
		return new Response<>(HttpStatus.SERVICE_UNAVAILABLE.value(), "Server problem occured", null);
	}

}
