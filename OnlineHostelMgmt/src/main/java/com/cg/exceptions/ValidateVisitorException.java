package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateVisitorException extends Exception {
	private List<FieldError> errors;
	
	public ValidateVisitorException() {
		super();
	}

	public ValidateVisitorException(String msg) {
		super(msg);
	}

	public ValidateVisitorException(List<FieldError> errors) {
		super();
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}


}
