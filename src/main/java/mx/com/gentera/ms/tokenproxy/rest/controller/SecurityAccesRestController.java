package mx.com.gentera.ms.tokenproxy.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.gentera.ms.tokenproxy.dto.CredencialsDTO;
import mx.com.gentera.ms.tokenproxy.dto.JwtDto;
import mx.com.gentera.ms.tokenproxy.dto.UserDTO;
import mx.com.gentera.ms.tokenproxy.exception.SecurityAccesException;
import mx.com.gentera.ms.tokenproxy.service.SecurityAccesService;

@RestController
@RequestMapping("securityAcces")
public class SecurityAccesRestController {
	@Autowired
	private SecurityAccesService securityAccesService;
	
	@PostMapping(value = "/getUserDtoByUserAndPassword")
	public UserDTO getUserDtoByUserAndPassword(@RequestBody(required = true) CredencialsDTO credencialsDTO) throws SecurityAccesException {
		return this.securityAccesService.getUserDtoByUserAndPassword(credencialsDTO.getUser(), credencialsDTO.getPassword());
	}
	
	@PostMapping(value = "/getUserDtoByRefreshtoken")
	public UserDTO getUserDtoByRefreshtoken(@RequestBody(required = true) JwtDto jwtDto) throws SecurityAccesException {
		return this.securityAccesService.getUserDtoByRefreshtoken(jwtDto.getRefresh_token());
	}
	
	@PostMapping(value = "/logoutUserByToken"	)
	public void logoutUserByToken(@RequestBody(required = true) JwtDto jwtDto) {
		this.securityAccesService.logoutUserByToken(jwtDto.getAccess_token());
	}	
}
