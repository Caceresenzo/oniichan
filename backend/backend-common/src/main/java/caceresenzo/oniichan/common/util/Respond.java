package caceresenzo.oniichan.common.util;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

import caceresenzo.oniichan.common.exception.ApiException;
import caceresenzo.oniichan.common.model.api.AbstractApiResponse;
import caceresenzo.oniichan.common.model.api.impl.ApiError;

public class Respond {

	public static ResponseEntity<?> by(Supplier<AbstractApiResponse> action) {
		try {
			return action.get().toResponseEntity();
		} catch (ApiException exception) {
			return new ApiError(exception.getStatus())
					.setMessage(exception.getMessage())
					.setDebugMessage(exception.getLocalizedMessage())
					.toResponseEntity();
		}
	}
	
}