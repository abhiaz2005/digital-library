package com.watsoo.catalog_service.service;

import com.watsoo.catalog_service.dto.CategoryDto;
import com.watsoo.catalog_service.dto.Response;

public interface CategoryService {
	public Response<?> createCategory(CategoryDto dto, Long userId);
}
