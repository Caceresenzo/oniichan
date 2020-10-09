package caceresenzo.oniichan.drive.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import caceresenzo.oniichan.common.model.api.impl.ApiAnwser;
import caceresenzo.oniichan.common.model.api.impl.ApiError;
import caceresenzo.oniichan.common.util.Respond;
import caceresenzo.oniichan.drive.entity.UploadedFile;
import caceresenzo.oniichan.drive.service.upload.IUploadedFileService;

@RestController
@RequestMapping(path = "/upload/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UploadRestController {
	
	private static String UPLOADED_FOLDER = "C:\\Users\\cacer\\Desktop\\";
	
	@Autowired
	private IUploadedFileService uploadFileService;
	
	@GetMapping
	public ResponseEntity<?> list(Pageable pageable) {
		return ApiAnwser.ofPage(uploadFileService.list(pageable)).toResponseEntity();
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> list(@PathVariable long userId, Pageable pageable) {
		return Respond.by(() -> ApiAnwser.ofPage(uploadFileService.listByUser(userId, pageable)));
	}
	
	@PostMapping
	public ResponseEntity<?> post(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return new ApiError(HttpStatus.LENGTH_REQUIRED).toResponseEntity();
		}
		
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		
		try {
			byte[] bytes = file.getBytes();
			Files.write(path, bytes);
		} catch (IOException e) {
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR).toResponseEntity();
		}
		
		return new ApiAnwser<>().setPayload(uploadFileService.create(new UploadedFile()
				.setName(file.getOriginalFilename())	
				.setPath(path.toAbsolutePath().toString()))).toResponseEntity();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		return Respond.by(() -> new ApiAnwser<>(uploadFileService.delete(id)));
	}
	
}