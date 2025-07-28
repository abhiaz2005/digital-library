package com.watsoo.borrow_service.service;

import com.watsoo.borrow_service.dto.LoginRequest;
import com.watsoo.borrow_service.dto.RegistrationDto;
import com.watsoo.borrow_service.dto.Response;

public interface UserService {

	Response<Object> generateToken(LoginRequest loginRequest);

	Response<Object> registerUser(RegistrationDto registrationDto);

	Response<?> getAllUsers(Long id);

	Response<?> getUserById(Long id);

}
