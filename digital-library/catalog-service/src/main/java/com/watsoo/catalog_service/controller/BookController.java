package com.watsoo.catalog_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watsoo.catalog_service.dto.BookDto;
import com.watsoo.catalog_service.dto.Response;
import com.watsoo.catalog_service.dto.SearchDto;
import com.watsoo.catalog_service.service.BookService;
import com.watsoo.catalog_service.service.ValidationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Book Catalog APIs", description = "Operations related to book creation, search, and retrieval")
public class BookController {

	@Autowired
	private ValidationService validationService;
	
	@Autowired
	private BookService bookService;

	@Operation(
		summary = "Register a new book",
		description = "Registers a new book after validating the BookDto payload",
		responses = {
			@ApiResponse(responseCode = "200", description = "Book registered successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input payload"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
		}
	)
	@PostMapping("create/book")
	public ResponseEntity<?> BookRegistration(@RequestBody BookDto registrationDto,@RequestParam(required = false) Long userId ) {
		Response<Object> validationResponse = validationService.checkForBookRegistrationPayload(registrationDto);
		if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
			Response<?> response = bookService.createBook(registrationDto,userId);
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
		}
		return new ResponseEntity<>(validationResponse, HttpStatus.valueOf(validationResponse.getResponseCode()));
	}

	@Operation(
		summary = "Search books with pagination",
		description = "Searches for books based on filters and pagination using SearchDto"
	)
	@PostMapping("search")
	public ResponseEntity<?> searchBooksPagination(@RequestBody SearchDto searchDTO) {
		Response<?> searchUsers = bookService.getAllBooksPaginate(searchDTO);
		return new ResponseEntity<>(searchUsers, HttpStatus.valueOf(searchUsers.getResponseCode()));
	}

	@Operation(
		summary = "Get book by ID",
		description = "Fetch details of a specific book using its book ID"
	)
	@GetMapping("get/books/{bookId}")
	public ResponseEntity<?> getBookById(@PathVariable Long bookId) {
		Response<?> response = bookService.getBookById(bookId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
