package mx.com.gentera.ms.tokenproxy.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import mx.com.gentera.ms.tokenproxy.dto.JwtDto;
import mx.com.gentera.ms.tokenproxy.dto.PersonDTO;
import mx.com.gentera.ms.tokenproxy.dto.UserDTO;
import mx.com.gentera.ms.tokenproxy.exception.SecurityAccesException;
import mx.com.gentera.ms.tokenproxy.remote.service.OauthRemoteService;
import mx.com.gentera.ms.tokenproxy.service.SecurityAccesService;

@Service
public class SecurityAccesServiceImpl implements SecurityAccesService{
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAccesServiceImpl.class);
			
	@Autowired
	private OauthRemoteService oauthRemoteService;
	
	@Override
	public UserDTO getUserDtoByUserAndPassword(final String user, final String password) throws SecurityAccesException {
		UserDTO userDto = null;
		try {
			JwtDto jwtDto = this.oauthRemoteService.getJwtDtoByUserAndPassword(user, password);		
			LOGGER.debug("JWT: {}", jwtDto);
			userDto = new UserDTO();
			DecodedJWT jwt = JWT.decode(jwtDto.getAccess_token());
			userDto.setJwt(jwtDto);
			userDto.setName(jwt.getClaim("user_name").asString());
			userDto.setRoles(jwt.getClaim("authorities").asList(String.class));
			userDto.setPerson(jwt.getClaim("person").as(PersonDTO.class));
			userDto.setLanguaje(jwt.getClaim("languaje").asString());
			userDto.setActiveRol(jwt.getClaim("active_rol").asString());
		}catch(HttpClientErrorException e) {
			LOGGER.warn("Error in oauthRemoteService", e);
			throw new SecurityAccesException();
		}
		return userDto;
	}
	
	@Override
	public UserDTO getUserDtoByRefreshtoken(final String refreshToken) throws SecurityAccesException {
		UserDTO userDto = null;
		try {
			JwtDto jwtDto = this.oauthRemoteService.getJwtDtoByRefreshToken(refreshToken);
			LOGGER.debug("JWT: {}", jwtDto);
			userDto = new UserDTO();
			DecodedJWT jwt = JWT.decode(jwtDto.getAccess_token());
			userDto.setJwt(jwtDto);
			userDto.setName(jwt.getClaim("user_name").asString());
			userDto.setRoles(jwt.getClaim("authorities").asList(String.class));		
		}catch(HttpClientErrorException e) {
			LOGGER.warn("Error in oauthRemoteService", e);
			throw new SecurityAccesException();
		}
		return userDto;
	}
	
	@Override
	public void logoutUserByToken(final String token) {
		LOGGER.debug("logoutUserByToken: {}", token);
		try {
			this.oauthRemoteService.logoutUserByToken(token);
		}catch(HttpClientErrorException e) {
			LOGGER.warn("Error in oauthRemoteService", e);
		}
	}

}
