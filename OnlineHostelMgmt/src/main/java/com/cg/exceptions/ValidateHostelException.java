package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateHostelException extends Exception {

	private List<FieldError> errors;

	public ValidateHostelException() {
		super();
	}

	public ValidateHostelException(String message) {
		super(message);
	}

	public ValidateHostelException(List<FieldError> errors) {
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
}
