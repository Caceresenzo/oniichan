package caceresenzo.oniichan.drive.service.upload;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import caceresenzo.oniichan.common.service.ICRUDService;
import caceresenzo.oniichan.drive.dto.entity.UploadedFileUpdateBody;
import caceresenzo.oniichan.drive.entity.UploadedFile;

public interface IUploadedFileService extends ICRUDService<UploadedFile, UploadedFile, UploadedFileUpdateBody, Long> {
	
	List<UploadedFile> listByUser(long userId);
	
	Page<UploadedFile> listByUser(long userId, Pageable pageable);
	
}