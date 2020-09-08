package mx.com.gentera.ms.tokenproxy.remote.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import mx.com.gentera.ms.tokenproxy.dto.JwtDto;
import mx.com.gentera.ms.tokenproxy.remote.service.OauthRemoteService;

@Slf4j
@Service
public class OauthRemoteServiceImpl implements OauthRemoteService {
	@Autowired
	@Qualifier("oauthServerRestTemplate")
	private RestTemplate restTemplate;
	@Value("${oauth.server.token.url}")
	private String url;
	@Value("${oauth.server.security.header}")
	private String headerName;
	
	@Override
	public JwtDto getJwtDtoByUserAndPassword(String user, String password) {
		log.debug("getJwtDtoByUserAndPassword()");
		StringBuilder st = new StringBuilder(this.url);
		st.append("/token?grant_type=password&username=").append(user).append("&password=").append(password);
		JwtDto jwtDto = restTemplate.postForObject(st.toString(), null, JwtDto.class);
		return jwtDto;
	}

	@Override
	public JwtDto getJwtDtoByRefreshToken(String refreshToken) {
		log.debug("getJwtDtoByRefreshToken()");
		StringBuilder st = new StringBuilder(this.url);
		st.append("/token?grant_type=refresh_token&refresh_token=").append(refreshToken);
		JwtDto jwtDto = restTemplate.postForObject(st.toString(), null, JwtDto.class);
		return jwtDto;
	}

	@Override
	public void logoutUserByToken(String token) {
		log.debug("logoutUserByToken()");
		RestTemplate restTemplateForLogout = new RestTemplate();
		restTemplateForLogout.getInterceptors().add(new ClientHttpRequestInterceptor() {
	       	    @Override
	     	    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
	     	        request.getHeaders().add(headerName, "Bearer "+token);
	     	        return execution.execute(request, body);
	     	    }
     		}
		);
		StringBuilder st = new StringBuilder(this.url).append("/logout");
		restTemplateForLogout.postForObject(st.toString(), null, String.class);
	}

}
