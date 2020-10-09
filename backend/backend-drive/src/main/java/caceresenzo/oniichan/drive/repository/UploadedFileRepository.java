package caceresenzo.oniichan.drive.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import caceresenzo.oniichan.drive.entity.UploadedFile;
import caceresenzo.oniichan.user.entity.User;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
	
	List<UploadedFile> findAllByOwner(User user);
	
	Page<UploadedFile> findAllByOwner(User user, Pageable pageable);
	
}