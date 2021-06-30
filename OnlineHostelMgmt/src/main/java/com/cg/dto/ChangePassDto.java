package com.cg.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cg.helper.LoginConstants;

public class ChangePassDto {

	@NotNull(message = LoginConstants.EMAIL_NOTNULL_MESSAGE)
	@Email(message = "Must be a valid email")
	private String email;
	@NotBlank(message = LoginConstants.PASSWORD_REQUIRED_MESSAGE)
	private String oldPass;
	@NotBlank(message = "New password is Required")
	private String newPass;
	@NotBlank(message = "Confirm new password is required")
	private String cnf_newPass;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getCnf_newPass() {
		return cnf_newPass;
	}

	public void setCnf_newPass(String cnf_newPass) {
		this.cnf_newPass = cnf_newPass;
	}

}
