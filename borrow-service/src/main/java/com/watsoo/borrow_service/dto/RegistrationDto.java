package com.watsoo.borrow_service.dto;

import com.watsoo.borrow_service.enums.Role;

public class RegistrationDto {
	private Long id ;
	
	private String name;
	
    private String email;
    
    private String password ;
    
    private Role role;
    
    private String phoneNumber;
    
    private Boolean isActive;
    
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}
	

	public void setRole(Role role) {
		this.role = role;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public RegistrationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegistrationDto(Long id, String name, String email, String password, Role role, String phoneNumber,
			Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.isActive = isActive;
	}

	
	

	
}
