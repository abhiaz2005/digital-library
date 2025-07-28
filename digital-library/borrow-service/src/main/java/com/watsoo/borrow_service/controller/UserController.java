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

import com.watsoo.borrow_service.dto.RegistrationDto;
import com.watsoo.borrow_service.dto.Response;
import com.watsoo.borrow_service.dto.UserDto;
import com.watsoo.borrow_service.service.UserService;
import com.watsoo.borrow_service.service.ValidationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User APIs", description = "User service API'S")
public class UserController {

	@Autowired
	private UserService userService ;
	
	@Autowired
	private ValidationService validationService ;
	
	@Operation(
			summary = "Create user",
			description = "for creating user"
		)
	@PostMapping("/create/user")
	public ResponseEntity<?> registerUser(@RequestBody RegistrationDto registrationDto) {
		Response<?> validationResponse =validationService.checkForUserRegistrationPayload(registrationDto);
		if(validationResponse.getResponseCode() == HttpStatus.OK.value()) {
			Response<?> response = userService.registerUser(registrationDto);
			return new ResponseEntity<>(response, HttpStatus.OK);			
		}
		return new ResponseEntity<>(validationResponse, HttpStatus.OK);			
	}
	
	@Operation(
			summary = "Get all users ",
			description = "Get all users data(Only for Admin)"
		)
	@GetMapping("get/all/users")
	public ResponseEntity<?> getAllUsers(@RequestParam(required = false) Long id) {
		Response<?> response = userService.getAllUsers(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	@Operation(
			summary = "Get user by Id",
			description = "Get User By UserId"
		)
	@GetMapping("get/user")
	public ResponseEntity<?> getUserById(@RequestParam(required = false) Long id) {
		Response<?> response = userService.getUserById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
