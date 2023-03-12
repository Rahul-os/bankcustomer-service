package com.deloitte.bankcustomer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServerExceptionHandler {
	@ExceptionHandler(value = ServerNotFoundException.class)
	public ResponseEntity<Object> exception(ServerNotFoundException exception)
	{
		return new ResponseEntity<Object>("Server down or customerdetails are empty!!!",HttpStatus.NOT_FOUND);
	}

}
