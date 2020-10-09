package caceresenzo.oniichan.drive.service.upload.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import caceresenzo.oniichan.drive.dto.entity.UploadedFileUpdateBody;
import caceresenzo.oniichan.drive.entity.UploadedFile;
import caceresenzo.oniichan.drive.repository.UploadedFileRepository;
import caceresenzo.oniichan.drive.service.upload.IUploadedFileService;
import caceresenzo.oniichan.user.service.user.IUserService;

@Service
public class UploadedFileServiceImpl implements IUploadedFileService {
	
	@Autowired
	private UploadedFileRepository repository;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public UploadedFile create(UploadedFile data) {
		return repository.save(data.setId(0).setOwner(userService.read(2l)));
	}
	
	@Override
	public UploadedFile update(Long id, UploadedFileUpdateBody data) {
		return repository.save(read(id).setName(data.getName()));
	}
	
	@Override
	public JpaRepository<UploadedFile, Long> getRepository() {
		return repository;
	}
	
	@Override
	public List<UploadedFile> listByUser(long userId) {
		return repository.findAllByOwner(userService.read(userId));
	}
	
	@Override
	public Page<UploadedFile> listByUser(long userId, Pageable pageable) {
		return repository.findAllByOwner(userService.read(userId), pageable);
	}
	
	@Override
	public Class<UploadedFile> getEntityClass() {
		return UploadedFile.class;
	}
	
}