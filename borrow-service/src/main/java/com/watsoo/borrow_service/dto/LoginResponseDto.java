package com.watsoo.borrow_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.watsoo.borrow_service.enums.Role;

@JsonInclude(Include.NON_NULL)
public class LoginResponseDto {

	private Long id;
	private String userName;
	private Role roleType;
	private String token;
	private Boolean isPassowrdResetRequired;
	private String email;
	private String phoneNo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Role getRoleType() {
		return roleType;
	}
	public void setRoleType(Role roleType) {
		this.roleType = roleType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getIsPassowrdResetRequired() {
		return isPassowrdResetRequired;
	}
	public void setIsPassowrdResetRequired(Boolean isPassowrdResetRequired) {
		this.isPassowrdResetRequired = isPassowrdResetRequired;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public LoginResponseDto(Long id, String userName, Role roleType, String token, Boolean isPassowrdResetRequired,
			String email, String phoneNo) {
		super();
		this.id = id;
		this.userName = userName;
		this.roleType = roleType;
		this.token = token;
		this.isPassowrdResetRequired = isPassowrdResetRequired;
		this.email = email;
		this.phoneNo = phoneNo;
	}
	public LoginResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginResponseDto(Long id, Role role, String name, String token, String email) {
		// TODO Auto-generated constructor stub
	}
	
	
}
