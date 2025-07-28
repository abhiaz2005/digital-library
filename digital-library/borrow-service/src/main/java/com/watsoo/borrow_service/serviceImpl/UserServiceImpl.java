package com.watsoo.borrow_service.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.watsoo.borrow_service.dto.LoginRequest;
import com.watsoo.borrow_service.dto.LoginResponseDto;
import com.watsoo.borrow_service.dto.RegistrationDto;
import com.watsoo.borrow_service.dto.Response;
import com.watsoo.borrow_service.dto.UserDto;
import com.watsoo.borrow_service.entity.User;
import com.watsoo.borrow_service.enums.Role;
import com.watsoo.borrow_service.repository.UserRepository;
import com.watsoo.borrow_service.security.CustomizedUserDetailsService;
import com.watsoo.borrow_service.service.JwtService;
import com.watsoo.borrow_service.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private CustomizedUserDetailsService customizedUserDetailsService;

	@Override
	public Response<Object> generateToken(LoginRequest loginRequest) {
		try {
			Optional<User> userOptional = userRepository.findByEmail(loginRequest.getUsername());
			if (userOptional.isPresent() && userOptional.get().getIsActive()) {
				User user = userOptional.get();

				if (user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.USER)
						|| user.getRole().equals(Role.USER)) {
					if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

						String token = jwtService.generateToken(loginRequest.getUsername());
						LoginResponseDto response = new LoginResponseDto();
						response.setToken(token);
						response.setUserName(user.getName());
						response.setEmail(user.getEmail());
						response.setRoleType(user.getRole());
						return new Response<>(HttpStatus.OK.value(), "Login Success.", response);
					}
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid credentials.", null);
				}
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "invalid credential", null);
			}
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "invalid credential", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "something went wrong", null);
		}
	}

	@Override
	public Response<Object> registerUser(RegistrationDto registrationDto) {
		try {

			if (registrationDto.getId() == null) {
				Optional<User> userOptional = userRepository.findByEmailOrPhoneNumber(registrationDto.getEmail(),
						registrationDto.getPhoneNumber());
				if (userOptional.isPresent()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "User already exists", null);
				}
				User user = new User();
				user.setName(registrationDto.getName());
				user.setEmail(registrationDto.getEmail());
				user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
				user.setPhoneNumber(registrationDto.getPhoneNumber());
				user.setRole(registrationDto.getRole());
				user.setIsActive(true);
				user.setCreatedAt(new Date());
				User savedUser = userRepository.save(user);
				return new Response<>(HttpStatus.CREATED.value(), "Created successfully",
						"userId :" + savedUser.getId());
			} else {
				Optional<User> userOptional = userRepository.findById(registrationDto.getId());
				if (!userOptional.isPresent()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "User does not exist", null);
				}
				User user = userOptional.get();
				if (registrationDto.getName() != null) {
					user.setName(registrationDto.getName());
				}
				if (registrationDto.getEmail() != null) {
					user.setEmail(registrationDto.getEmail());
				}
				if (registrationDto.getPhoneNumber() != null) {
					user.setPhoneNumber(registrationDto.getPhoneNumber());
				}
				user.setUpdatedAt(new Date());
				User updatedUser = userRepository.save(user);
				return new Response<>(HttpStatus.CREATED.value(), "Updated successfully",
						"userId :" + updatedUser.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "something went wrong", null);
		}
	}

	@Override
	public Response<?> getAllUsers(Long id) {
		try {
			if(id == null) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "User-ID is required", null);
			}
			Optional<User> userOptional = userRepository.findById(id);
			if (!userOptional.isPresent()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "User does not exist", null);
			}

			if (userOptional.get().getRole().equals(Role.ADMIN)) {
				List<User> users = userRepository.findAll();
				List<UserDto> userDtos = users.stream().map(e -> e.convertToDto()).collect(Collectors.toList());
				return new Response<>(HttpStatus.OK.value(), "OK", userDtos);
			}

			return new Response<>(HttpStatus.OK.value(), "You don't have permission for this route", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "something went wrong", null);
		}
	}

	@Override
	public Response<?> getUserById(Long id) {
		try {
			if(id == null) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "User-ID is required", null);
			}
			Optional<User> userOptional = userRepository.findById(id);
			if (!userOptional.isPresent()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "User does not exist", null);
			}

			UserDto userDto = userOptional.get().convertToDto();
			return new Response<>(HttpStatus.OK.value(), "OK", userDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "something went wrong", null);
		}
	}
}
