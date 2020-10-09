package caceresenzo.oniichan.auth.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import caceresenzo.oniichan.auth.model.UserPrincipal;
import caceresenzo.oniichan.auth.service.token.IAuthTokenService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private IAuthTokenService tokenService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
		String authorizationHeader = httpServletRequest.getHeader("Authorization");
		
		UsernamePasswordAuthenticationToken token;
		if (authorizationHeaderIsInvalid(authorizationHeader) || (token = createToken(authorizationHeader)) == null) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		
		SecurityContextHolder.getContext().setAuthentication(token);
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
	
	private boolean authorizationHeaderIsInvalid(String authorizationHeader) {
		return authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
	}
	
	private UsernamePasswordAuthenticationToken createToken(String authorizationHeader) {
		try {
			String token = authorizationHeader.replace("Bearer ", "");
			UserPrincipal userPrincipal = tokenService.parseToken(token);
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			if (userPrincipal.isAdmin()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			
			return new UsernamePasswordAuthenticationToken(userPrincipal, null, authorities);
		} catch (Exception exception) {
			return null;
		}
	}
}