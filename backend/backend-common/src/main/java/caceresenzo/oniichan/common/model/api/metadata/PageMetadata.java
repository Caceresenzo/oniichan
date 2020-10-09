package caceresenzo.oniichan.common.model.api.metadata;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(chain = true)
public class PageMetadata {
	
	/* Variables */
	private int size;
	private long totalElements;
	private int totalPages;
	private int page;
	
	/* Constructor */
	public PageMetadata(Page<?> page) {
		this(page.getSize(), page.getTotalElements(), page.getTotalPages(), page.getNumber());
	}
	
}