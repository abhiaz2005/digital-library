package com.watsoo.borrow_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watsoo.borrow_service.dto.BorrowDto;
import com.watsoo.borrow_service.dto.Response;
import com.watsoo.borrow_service.service.BorrowingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Borrow Service APIs", description = "APIs related to borrowing and returning books")
public class BorrowingController {

	@Autowired
	private BorrowingService borrowingService;

	@Operation(summary = "Borrow a book", description = "Allows a user to borrow a book by passing required BorrowDto details")
	@PostMapping("borrow/book")
	public ResponseEntity<?> borrowBook(@RequestBody BorrowDto borrowDto) {
		Response<?> response = borrowingService.borrowBook(borrowDto);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}

	@Operation(summary = "Return a borrowed book", description = "Allows a user to return a previously borrowed book by providing borrowId")
	@PostMapping("return/book/{borrowId}")
	public ResponseEntity<?> returnBook(@PathVariable Long borrowId) {
		Response<?> response = borrowingService.returnBook(borrowId);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}

	@Operation(summary = "Get all borrow books", description = "Admin can show who can borrow books and the due date and who have not returned or who have late return date")
	@GetMapping("get/all/borrowing/books")
	public ResponseEntity<?> getAllBorrows(@RequestParam Long userId) {
		Response<?> response = borrowingService.getAllBorrows(userId);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}

}
