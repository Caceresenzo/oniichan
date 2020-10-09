package caceresenzo.oniichan.drive.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import caceresenzo.oniichan.user.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "uploaded_files")
@Data
@Accessors(chain = true)
public class UploadedFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id")
	private User owner;
	
	@Column
	private String name;
	
	@Column
	private String path;
	
	@Column
	private LocalDateTime uploadedAt = LocalDateTime.now();
	
}