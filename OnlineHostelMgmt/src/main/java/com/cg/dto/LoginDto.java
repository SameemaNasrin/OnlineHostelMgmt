package com.cg.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cg.helper.LoginConstants;

/*
 * @Author: Supriyo Das
 */
public class LoginDto {

	@NotNull(message = LoginConstants.EMAIL_NOTNULL_MESSAGE)
	private String email;

	@NotBlank(message = LoginConstants.PASSWORD_REQUIRED_MESSAGE)
	private String password;

	@NotNull(message = "Role is mandatory")
	private String role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

}
