package com.watsoo.catalog_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watsoo.catalog_service.dto.CategoryDto;
import com.watsoo.catalog_service.dto.Response;
import com.watsoo.catalog_service.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Category APIs", description = "Operations related to book categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@Operation(
			summary = "Create a new book category",
			description = "Adds a new category to the catalog for book classification"
		)
	@PostMapping("create/category")
	public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto,@RequestParam(required = false) Long userId ) {
		Response<?> response = categoryService.createCategory(categoryDto,userId);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}
}
