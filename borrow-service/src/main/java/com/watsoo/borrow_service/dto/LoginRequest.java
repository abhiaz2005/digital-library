package com.watsoo.borrow_service.dto;

public class LoginRequest {

	private String username;
	private String password;
	private String otp;
	private String newPassword;
	private Boolean isPhone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Boolean getIsPhone() {
		return isPhone;
	}

	public void setIsPhone(Boolean isPhone) {
		this.isPhone = isPhone;
	}

}
