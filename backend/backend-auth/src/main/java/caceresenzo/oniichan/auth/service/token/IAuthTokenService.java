package caceresenzo.oniichan.auth.service.token;

import caceresenzo.oniichan.auth.entity.UserAuthData;
import caceresenzo.oniichan.auth.model.AuthenticatedUser;
import caceresenzo.oniichan.auth.model.UserPrincipal;

public interface IAuthTokenService {
	
	AuthenticatedUser generateToken(UserAuthData authData);
	
	UserPrincipal parseToken(String token);
	
}