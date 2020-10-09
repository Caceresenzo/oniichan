package caceresenzo.oniichan.auth.exception;

import org.springframework.http.HttpStatus;

import caceresenzo.oniichan.common.exception.ApiException;

public class BadCredentialsException extends ApiException {
	
	public BadCredentialsException() {
		super("email or password is invalid");
	}
	
	@Override
	public HttpStatus getStatus() {
		return HttpStatus.UNAUTHORIZED;
	}
	
}