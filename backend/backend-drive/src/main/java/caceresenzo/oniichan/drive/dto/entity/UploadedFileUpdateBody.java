package caceresenzo.oniichan.drive.dto.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UploadedFileUpdateBody {
	
	private String name;
	
}