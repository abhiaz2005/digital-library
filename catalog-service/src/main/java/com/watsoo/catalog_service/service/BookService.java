package com.watsoo.catalog_service.service;

import com.watsoo.catalog_service.dto.BookDto;
import com.watsoo.catalog_service.dto.Response;
import com.watsoo.catalog_service.dto.SearchDto;

public interface BookService {
	 Response<?> createBook(BookDto dto, Long userId);
	 Response<?> getAllBooksPaginate(SearchDto searchDto);
	 Response<?>getBookById(Long bookId);
	  
}
