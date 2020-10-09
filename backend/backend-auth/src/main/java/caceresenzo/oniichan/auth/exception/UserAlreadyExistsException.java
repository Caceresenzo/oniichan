package caceresenzo.oniichan.auth.exception;

import org.springframework.http.HttpStatus;

import caceresenzo.oniichan.common.exception.ApiException;

public class UserAlreadyExistsException extends ApiException {
	
	public UserAlreadyExistsException() {
		super("user already exists");
	}
	
	@Override
	public HttpStatus getStatus() {
		return HttpStatus.CONFLICT;
	}
	
}