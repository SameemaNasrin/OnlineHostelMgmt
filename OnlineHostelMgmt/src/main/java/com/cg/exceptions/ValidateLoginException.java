package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateLoginException extends Exception {
	private List<FieldError> errors;

	public ValidateLoginException() {
		super();
	}

	public ValidateLoginException(String message) {
		super(message);
	}
	
	public ValidateLoginException(List<FieldError> errors) {
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
	
}
