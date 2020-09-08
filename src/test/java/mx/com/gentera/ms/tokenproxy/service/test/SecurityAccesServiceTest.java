package mx.com.gentera.ms.tokenproxy.service.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import mx.com.gentera.ms.tokenproxy.TokenProxyMs;
import mx.com.gentera.ms.tokenproxy.dto.UserDTO;
import mx.com.gentera.ms.tokenproxy.exception.SecurityAccesException;
import mx.com.gentera.ms.tokenproxy.service.SecurityAccesService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenProxyMs.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(locations = "classpath:application.yml")
public class SecurityAccesServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAccesServiceTest.class);

	@Autowired
	private SecurityAccesService securityAccesService;
	private String user = "admin";
	private String password = "password";
	
	@Test
	public void getUserDtoByUserAndPasswordTest() throws SecurityAccesException {
		UserDTO userDto = securityAccesService.getUserDtoByUserAndPassword(user, password);
		LOGGER.debug("userDto: {}",userDto);
		assertNotNull(userDto);
	}
	@Test
	public void getUserDtoByRefreshtokenTest() throws SecurityAccesException {
		UserDTO userDto = securityAccesService.getUserDtoByUserAndPassword(user, password);
		userDto = securityAccesService.getUserDtoByRefreshtoken(userDto.getJwt().getRefresh_token());
		LOGGER.debug("userDto Refresh: {}",userDto);
		assertNotNull(userDto);
	}
	@Test
	public void logoutUserByToken() throws SecurityAccesException {
		UserDTO userDto = securityAccesService.getUserDtoByUserAndPassword(user, password);
		securityAccesService.logoutUserByToken(userDto.getJwt().getAccess_token());
	}
	
	
}
