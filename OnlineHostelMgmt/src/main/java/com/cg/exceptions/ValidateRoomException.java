package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateRoomException extends Exception {
private List<FieldError> errors;
	
	public ValidateRoomException() {
		super();
	}

	public ValidateRoomException(String message) {
		super(message);
	}
	
	public ValidateRoomException(List<FieldError> errors)	{
		this.errors= errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}

}
