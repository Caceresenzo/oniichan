package caceresenzo.oniichan.auth.service.user;

import caceresenzo.oniichan.auth.dto.form.LoginForm;
import caceresenzo.oniichan.auth.dto.form.RegisterForm;
import caceresenzo.oniichan.auth.model.AuthenticatedUser;

public interface IUserAuthService {
	
	AuthenticatedUser login(LoginForm form);
	
	AuthenticatedUser register(RegisterForm form);
	
}