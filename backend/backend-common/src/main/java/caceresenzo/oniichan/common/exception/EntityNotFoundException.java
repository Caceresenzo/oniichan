package caceresenzo.oniichan.common.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApiException {
	
	public EntityNotFoundException() {
		super();
	}
	
	public EntityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EntityNotFoundException(String message) {
		super(message);
	}
	
	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}
	
	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}
	
	public static <I> EntityNotFoundException create(I id) {
		return new EntityNotFoundException(String.format("id `%s` not found", id));
	}
	
	public static <I> EntityNotFoundException create(String modelName, I id) {
		return new EntityNotFoundException(String.format("`%s` with id `%s` not found", modelName, id));
	}
	
	public static <I> EntityNotFoundException create(Class<?> modelClass, I id) {
		return new EntityNotFoundException(String.format("`%s` with id `%s` not found", modelClass.getSimpleName(), id));
	}
	
	public static <I> EntityNotFoundException create(Class<?> modelClass, I id, String idName) {
		return new EntityNotFoundException(String.format("`%s` with %s `%s` not found", modelClass.getSimpleName(), idName, id));
	}
	
}