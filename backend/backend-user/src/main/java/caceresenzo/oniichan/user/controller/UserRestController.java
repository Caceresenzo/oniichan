package caceresenzo.oniichan.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import caceresenzo.oniichan.common.model.api.impl.ApiAnwser;
import caceresenzo.oniichan.common.util.Respond;
import caceresenzo.oniichan.user.dto.entity.UserUpdateBody;
import caceresenzo.oniichan.user.service.user.IUserService;

@RestController
@RequestMapping(path = "/user/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping
	public ResponseEntity<?> list(Pageable pageable) {
		return ApiAnwser.ofPage(userService.list(pageable)).toResponseEntity();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody UserUpdateBody body) {
		return new ApiAnwser<>(userService.create(body)).toResponseEntity();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable long id) {
		return Respond.by(() -> new ApiAnwser<>(userService.read(id)));
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody UserUpdateBody body) {
		return Respond.by(() -> new ApiAnwser<>(userService.update(id, body)));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		return Respond.by(() -> new ApiAnwser<>(userService.delete(id)));
	}
	
}