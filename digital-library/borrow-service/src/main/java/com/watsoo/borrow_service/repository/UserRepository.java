package com.watsoo.borrow_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watsoo.borrow_service.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String username);

	Optional<User>  findByEmailOrPhoneNumber(String email, String phoneNumber);
}
