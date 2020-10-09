package caceresenzo.oniichan.user.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import caceresenzo.oniichan.common.exception.EntityNotFoundException;
import caceresenzo.oniichan.user.dto.entity.UserUpdateBody;
import caceresenzo.oniichan.user.entity.User;
import caceresenzo.oniichan.user.repository.UserRepository;
import caceresenzo.oniichan.user.service.user.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public User create(UserUpdateBody data) {
		return repository.save(new User()
				.setEmail(data.getEmail())
				.setName(data.getName()));
	}
	
	@Override
	public User update(Long id, UserUpdateBody data) {
		return repository.save(read(id)
				.setEmail(data.getEmail())
				.setName(data.getName()));
	}
	
	@Override
	public JpaRepository<User, Long> getRepository() {
		return repository;
	}
	
	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}
	
	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> EntityNotFoundException.create(User.class, email, "email"));
	}
	
	@Override
	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}
	
}