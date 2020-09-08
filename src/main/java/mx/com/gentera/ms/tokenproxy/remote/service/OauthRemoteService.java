package mx.com.gentera.ms.tokenproxy.remote.service;

import mx.com.gentera.ms.tokenproxy.dto.JwtDto;

public interface OauthRemoteService {
	 JwtDto getJwtDtoByUserAndPassword(final String user, final String password);	
	 JwtDto getJwtDtoByRefreshToken(final String refreshToken);	
	 void logoutUserByToken(final String token);
}
