package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateAdminException extends Exception {
	private List<FieldError> errors;

	public ValidateAdminException() {
		super();
	}

	public ValidateAdminException(String msg) {
		super(msg);
	}

	public ValidateAdminException(List<FieldError> errors) {
		super();
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
}
