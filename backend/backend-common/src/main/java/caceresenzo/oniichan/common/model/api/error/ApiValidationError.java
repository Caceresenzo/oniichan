package caceresenzo.oniichan.common.model.api.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {
	
	/* Variables */
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;
	
	/* Constructor */
    public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
	
}