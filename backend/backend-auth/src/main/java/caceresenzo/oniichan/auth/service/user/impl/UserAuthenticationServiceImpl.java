package caceresenzo.oniichan.auth.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import caceresenzo.oniichan.auth.dto.form.LoginForm;
import caceresenzo.oniichan.auth.dto.form.RegisterForm;
import caceresenzo.oniichan.auth.dto.form.base.IAuthenticationForm;
import caceresenzo.oniichan.auth.entity.UserAuthData;
import caceresenzo.oniichan.auth.exception.BadCredentialsException;
import caceresenzo.oniichan.auth.exception.UserAlreadyExistsException;
import caceresenzo.oniichan.auth.model.AuthenticatedUser;
import caceresenzo.oniichan.auth.repository.UserAuthenticationDataRepository;
import caceresenzo.oniichan.auth.service.token.IAuthTokenService;
import caceresenzo.oniichan.auth.service.user.IUserAuthService;
import caceresenzo.oniichan.user.dto.entity.UserUpdateBody;
import caceresenzo.oniichan.user.entity.User;
import caceresenzo.oniichan.user.service.user.IUserService;

@Service
public class UserAuthenticationServiceImpl implements IUserAuthService {
	
	@Autowired
	private IAuthTokenService authTokenService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserAuthenticationDataRepository repository;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public AuthenticatedUser login(LoginForm form) {
		User user = userService.findByEmail(form.getEmail());
		UserAuthData authData = repository.findByUser(user).orElseThrow(IllegalStateException::new);
		
		if (!passwordEncoder.matches(form.getPassword(), authData.getEncodedPassword())) {
			throw new BadCredentialsException();
		}
		
		return authTokenService.generateToken(authData);
	}
	
	@Override
	public AuthenticatedUser register(RegisterForm form) {
		if (userService.existsByEmail(form.getEmail())) {
			throw new UserAlreadyExistsException();
		}
		
		return authTokenService.generateToken(repository
				.save(new UserAuthData()
						.setEncodedPassword(encodePassword(form))
						.setUser(userService.create(new UserUpdateBody()
								.setEmail(form.getEmail())
								.setName(form.getName())))));
	}
	
	private String encodePassword(IAuthenticationForm form) {
		return passwordEncoder.encode(form.getPassword());
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
}