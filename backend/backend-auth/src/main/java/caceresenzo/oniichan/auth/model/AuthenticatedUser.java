package caceresenzo.oniichan.auth.model;

import caceresenzo.oniichan.user.entity.User;
import caceresenzo.oniichan.user.model.UserRole;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthenticatedUser {

	private UserRole role;
	private User user;
	private String authorization;
	
}