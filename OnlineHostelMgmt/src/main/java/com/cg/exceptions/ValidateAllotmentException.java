package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateAllotmentException extends Exception {
	private List<FieldError> errors;

	public ValidateAllotmentException() {
		super();
	}

	public ValidateAllotmentException(String message) {
		super(message);
	}

	public ValidateAllotmentException(List<FieldError> errors) {
		super();
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
	}
}
