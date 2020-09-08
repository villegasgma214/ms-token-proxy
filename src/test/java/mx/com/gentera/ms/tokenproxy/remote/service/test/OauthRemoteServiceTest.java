package mx.com.gentera.ms.tokenproxy.remote.service.test;

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
import mx.com.gentera.ms.tokenproxy.dto.JwtDto;
import mx.com.gentera.ms.tokenproxy.remote.service.OauthRemoteService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenProxyMs.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(locations = "classpath:application.yml")
public class OauthRemoteServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(OauthRemoteServiceTest.class);
	@Autowired
	private OauthRemoteService oauthRemoteService;
	private String user = "admin";
	private String password = "password";
	
	@Test
	public void getJwtDtoByUSerAndPasswordTest() {
		JwtDto jwtDto = this.oauthRemoteService.getJwtDtoByUserAndPassword(user, password);
		LOGGER.debug("JWT: {}", jwtDto);
		assertNotNull(jwtDto);
	}
	@Test
	public void getJwtDtoByRefreshTokenTest() {
		JwtDto jwtDto = this.oauthRemoteService.getJwtDtoByUserAndPassword(user, password);
		jwtDto = oauthRemoteService.getJwtDtoByRefreshToken(jwtDto.getRefresh_token());
		LOGGER.debug("JWT refresh: {}", jwtDto);
		assertNotNull(jwtDto);
	}
	@Test
	public void logoutUserByTokenTest() {
		JwtDto jwtDto = this.oauthRemoteService.getJwtDtoByUserAndPassword(user, password);
		LOGGER.debug("JWT: {}", jwtDto);
		oauthRemoteService.logoutUserByToken(jwtDto.getAccess_token());
	}

}

