package caceresenzo.oniichan.common.model.api;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public abstract class AbstractApiResponse {

	/* Variables */
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private Object metadata;

	/* Constructor */
	private AbstractApiResponse() {
		this.timestamp = LocalDateTime.now();
	}

	/* Constructor */
	public AbstractApiResponse(HttpStatus status) {
		this();
		
		this.status = status;
	}

	/* Constructor */
	public AbstractApiResponse(HttpStatus status, Object metadata) {
		this(status);
		
		this.metadata = metadata;
	}
	
	public ResponseEntity<? extends AbstractApiResponse> toResponseEntity() {
		return new ResponseEntity<>(this, status);
	}
	
}