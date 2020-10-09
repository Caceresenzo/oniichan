package caceresenzo.oniichan.common.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException {
	
	public ApiException() {
		super();
	}
	
	public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ApiException(String message) {
		super(message);
	}
	
	public ApiException(Throwable cause) {
		super(cause);
	}
	
	public abstract HttpStatus getStatus();
	
}