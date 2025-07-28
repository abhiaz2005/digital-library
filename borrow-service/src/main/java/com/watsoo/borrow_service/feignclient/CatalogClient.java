package com.watsoo.borrow_service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.watsoo.borrow_service.dto.BookDto;
import com.watsoo.borrow_service.dto.Response;

@FeignClient(name = "catalog-service")
public interface CatalogClient {

	@GetMapping("get/books/{bookId}")
    Response<BookDto> getBookById(@PathVariable Long bookId);

}
