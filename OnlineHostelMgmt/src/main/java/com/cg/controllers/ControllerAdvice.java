package com.cg.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.dto.ErrorMessage;
import com.cg.exceptions.FloorNotFoundException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.exceptions.ValidateHostelException;

@RestControllerAdvice
public class ControllerAdvice {

	// HostelNotFoundException
	@ExceptionHandler(HostelNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionHostelNotFound(HostelNotFoundException ex) {
		List<String> list = new ArrayList<>();
		list.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), list);
	}

	// HttpMessageNotReadableException
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleException(HttpMessageNotReadableException ex) {
		List<String> list = new ArrayList<>();
		list.add("expected a value");
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), list);
	}

	// gives validation messages
	@ExceptionHandler(ValidateHostelException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleException(ValidateHostelException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), errors);
	}
	
	//RoomNotFoundException
	@ExceptionHandler(RoomNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionRoomNotFound(RoomNotFoundException ex) {
		List<String> list = new ArrayList<>();
		list.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), list);
	}
	
	//FloorNotFoundException
	@ExceptionHandler(FloorNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionFloorNotFound(FloorNotFoundException ex) {
		List<String> list = new ArrayList<>();
		list.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), list);
	}
}
