package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateFeeStructureException extends Exception {

	private List<FieldError> errors;

	public ValidateFeeStructureException() {
		super();
	}

	public ValidateFeeStructureException(String message) {
		super(message);
	}

	public ValidateFeeStructureException(List<FieldError> errors) {
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
