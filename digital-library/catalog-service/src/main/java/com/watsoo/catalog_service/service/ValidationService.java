package com.watsoo.catalog_service.service;

import com.watsoo.catalog_service.dto.BookDto;
import com.watsoo.catalog_service.dto.Response;

public interface ValidationService {

	Response<Object> checkForBookRegistrationPayload(BookDto registrationDto);

	
}
