package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateStudentException extends Exception {

	private List<FieldError> errors;

	public ValidateStudentException() {
		super();
	}

	public ValidateStudentException(String msg) {
		super(msg);
	}

	public ValidateStudentException(List<FieldError> errors) {
		super();
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}

}
