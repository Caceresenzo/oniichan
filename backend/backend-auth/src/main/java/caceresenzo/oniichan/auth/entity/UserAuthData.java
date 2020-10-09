package caceresenzo.oniichan.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import caceresenzo.oniichan.user.entity.User;
import caceresenzo.oniichan.user.model.UserRole;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "user_authentication_data")
@Data
@Accessors(chain = true)
public class UserAuthData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private long id;
	
	@Column
	@JsonIgnore
	private String encodedPassword;
	
	@OneToOne
	@NotNull
	private User user;

	@Column
	private UserRole role = UserRole.NORMAL;
	
}