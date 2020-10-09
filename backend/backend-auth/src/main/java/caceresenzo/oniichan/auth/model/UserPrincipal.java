package caceresenzo.oniichan.auth.model;

import caceresenzo.oniichan.user.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class UserPrincipal {
	
	private long id;
	private String name;
	private String email;
	private UserRole role;
	
	public boolean isAdmin() {
		return UserRole.ADMIN.equals(role);
	}
	
}