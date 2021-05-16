package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateWardenException extends Exception{
	
private List<FieldError> errors;
	
	public ValidateWardenException() {
		super();
	}

	public ValidateWardenException(String message) {
		super(message);
	}
	
	public ValidateWardenException(List<FieldError> errors)	{
		this.errors= errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
}
