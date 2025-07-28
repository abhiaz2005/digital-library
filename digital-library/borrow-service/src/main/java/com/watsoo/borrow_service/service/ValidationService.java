package com.watsoo.borrow_service.service;

import com.watsoo.borrow_service.dto.RegistrationDto;
import com.watsoo.borrow_service.dto.Response;
import com.watsoo.borrow_service.dto.UserDto;

public interface ValidationService {

	Response<?> checkForUserRegistrationPayload(RegistrationDto registrationDto);

	boolean checkFullName(String name);

	boolean checkMail(String mail);

	boolean checkPhoneNo(String phoneNo);

}
