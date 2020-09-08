package mx.com.gentera.ms.tokenproxy.service;

import mx.com.gentera.ms.tokenproxy.dto.UserDTO;
import mx.com.gentera.ms.tokenproxy.exception.SecurityAccesException;

public interface SecurityAccesService {

	UserDTO getUserDtoByUserAndPassword(final String user, final String password) throws SecurityAccesException ;
	
	UserDTO getUserDtoByRefreshtoken(final String refreshToken) throws SecurityAccesException ;
	
	void logoutUserByToken(final String token);
}
