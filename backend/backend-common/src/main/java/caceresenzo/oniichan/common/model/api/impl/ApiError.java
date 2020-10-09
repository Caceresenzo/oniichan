package caceresenzo.oniichan.common.model.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import caceresenzo.oniichan.common.model.api.AbstractApiResponse;
import caceresenzo.oniichan.common.model.api.error.ApiSubError;
import caceresenzo.oniichan.common.model.api.error.ApiValidationError;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ApiError extends AbstractApiResponse {
	
	/* Variables */
	private String message;
	private String debugMessage;
	private List<ApiSubError> subErrors;
	
	/* Constructor */
	public ApiError(HttpStatus status) {
		this(status, null, null);
	}
	
	/* Constructor */
	public ApiError(HttpStatus status, Throwable throwable) {
		this(status, null, throwable);
	}
	
	/* Constructor */
	public ApiError(HttpStatus status, String message, Throwable throwable) {
		super(status);
		
		this.message = message;
		this.debugMessage = throwable != null ? throwable.getLocalizedMessage() : null;
	}
	
	private ApiError addSubError(ApiSubError subError) {
		if (subErrors == null) {
			subErrors = new ArrayList<>();
		}
		
		subErrors.add(subError);
		
		return this;
	}
	
	private ApiError addValidationError(String object, String field, Object rejectedValue, String message) {
		return addSubError(new ApiValidationError(object, field, rejectedValue, message));
	}
	
	private ApiError addValidationError(String object, String message) {
		return addSubError(new ApiValidationError(object, message));
	}
	
	private ApiError addValidationError(FieldError fieldError) {
		return addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
	}
	
	public ApiError addValidationErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(this::addValidationError);
		
		return this;
	}
	
	private ApiError addValidationError(ObjectError objectError) {
		return addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
	}
	
	public ApiError addValidationError(List<ObjectError> globalErrors) {
		globalErrors.forEach(this::addValidationError);
		
		return this;
	}
	
	private ApiError addValidationError(ConstraintViolation<?> violation) {
		return addValidationError(violation.getRootBeanClass().getSimpleName(), ((PathImpl) violation.getPropertyPath()).getLeafNode().asString(), violation.getInvalidValue(), violation.getMessage());
	}
	
	public ApiError addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
		constraintViolations.forEach(this::addValidationError);
		
		return this;
	}
	
}