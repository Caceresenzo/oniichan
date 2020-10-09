package caceresenzo.oniichan.user.service.user;

import caceresenzo.oniichan.common.service.ICRUDService;
import caceresenzo.oniichan.user.dto.entity.UserUpdateBody;
import caceresenzo.oniichan.user.entity.User;

public interface IUserService extends ICRUDService<User, UserUpdateBody, UserUpdateBody, Long> {

	User findByEmail(String email);

	boolean existsByEmail(String email);
	
}