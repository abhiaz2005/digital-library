package com.watsoo.borrow_service.serviceImpl;

import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.watsoo.borrow_service.dto.RegistrationDto;
import com.watsoo.borrow_service.dto.Response;
import com.watsoo.borrow_service.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {

	@Override
	public Response<Object> checkForUserRegistrationPayload(RegistrationDto dto) {
		if (dto == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide data for registration.", null);
		}

		// Create
		if (dto.getId() == null) {

			if (dto.getName() == null || dto.getName().isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide name.", null);
			} else if (!checkFullName(dto.getName())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide valid name.", null);
			} else if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide password.", null);
			} else if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide email.", null);
			} else if (!checkMail(dto.getEmail())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide valid email.", null);
			} else if (dto.getPhoneNumber() == null || dto.getPhoneNumber().isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide phone number.", null);
			} else if (!checkPhoneNo(dto.getPhoneNumber())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide valid phone number.", null);
			} else if (dto.getRole() == null) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide ROLE[USER/ADMIN].", null);
			}

		} else {
			// Update
			if ((dto.getName() == null || dto.getName().isEmpty())
					&& (dto.getEmail() == null || dto.getEmail().isEmpty())
					&& (dto.getPhoneNumber() == null || dto.getPhoneNumber().isEmpty())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide at least one field for update.",
						null);
			}
		}

		return new Response<>(HttpStatus.OK.value(), "OK", null);
	}

	// Name Validation
	@Override
	public boolean checkFullName(String name) {
		return Pattern.matches("^[a-zA-Z](\\.|[a-zA-Z]+)(\\s[a-zA-Z]+|\\s[a-zA-Z]\\.){0,10}$", name);
	}

	// Email Validation
	@Override
	public boolean checkMail(String mail) {
		return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", mail);
	}

	@Override
	public boolean checkPhoneNo(String phoneNo) {
		return Pattern.matches("^(\\+91)?\\d{10}$", phoneNo);
	}

}
