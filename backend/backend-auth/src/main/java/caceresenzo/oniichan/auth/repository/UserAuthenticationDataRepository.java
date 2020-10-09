package caceresenzo.oniichan.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import caceresenzo.oniichan.auth.entity.UserAuthData;
import caceresenzo.oniichan.user.entity.User;

@Repository
public interface UserAuthenticationDataRepository extends JpaRepository<UserAuthData, Long> {
	
	Optional<UserAuthData> findByUser(User user);
	
}