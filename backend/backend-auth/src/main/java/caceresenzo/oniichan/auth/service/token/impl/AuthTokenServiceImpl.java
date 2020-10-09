package caceresenzo.oniichan.auth.service.token.impl;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import caceresenzo.oniichan.auth.entity.UserAuthData;
import caceresenzo.oniichan.auth.model.AuthenticatedUser;
import caceresenzo.oniichan.auth.model.UserPrincipal;
import caceresenzo.oniichan.auth.service.token.IAuthTokenService;
import caceresenzo.oniichan.user.model.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthTokenServiceImpl implements IAuthTokenService {
	
	@Value("${JWT_SECRET}")
	private String jwtSecret;
	
	@Override
	public AuthenticatedUser generateToken(UserAuthData authData) {
		Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS);
		Date expirationDate = Date.from(expirationTime);
		
		Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
		
		String compactTokenString = Jwts.builder()
				.claim("id", authData.getId())
				.claim("name", authData.getUser().getName())
				.claim("email", authData.getUser().getEmail())
				.claim("role", authData.getRole().name())
				.setExpiration(expirationDate)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		return new AuthenticatedUser()
				.setAuthorization("Bearer " + compactTokenString)
				.setRole(authData.getRole())
				.setUser(authData.getUser());
	}
	
	@Override
	public UserPrincipal parseToken(String token) {
		byte[] secretBytes = jwtSecret.getBytes();
		
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(secretBytes)
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		String name = claims.get("name", String.class);
		String email = claims.get("email", String.class);
		long userId = claims.get("id", Long.class);
		UserRole role = UserRole.valueOf(claims.get("role", String.class));
		
		return new UserPrincipal(userId, name, email, role);
	}
	
}