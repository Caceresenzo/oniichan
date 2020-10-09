package caceresenzo.oniichan.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import caceresenzo.oniichan.auth.dto.form.LoginForm;
import caceresenzo.oniichan.auth.dto.form.RegisterForm;
import caceresenzo.oniichan.auth.service.user.IUserAuthService;
import caceresenzo.oniichan.common.model.api.impl.ApiAnwser;
import caceresenzo.oniichan.common.util.Respond;

@Validated
@RestController
@RequestMapping(path = "/auth/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthRestController {
	
	@Autowired
	private IUserAuthService userAuthService;
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginForm loginForm) {
		return Respond.by(() -> new ApiAnwser<>(userAuthService.login(loginForm)));
	}
	
	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody @Valid RegisterForm registerForm) {
		return Respond.by(() -> new ApiAnwser<>(userAuthService.register(registerForm)));
	}
	
}