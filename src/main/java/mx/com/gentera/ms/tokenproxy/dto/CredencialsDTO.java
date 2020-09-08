package mx.com.gentera.ms.tokenproxy.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CredencialsDTO {
	private String user;
	private String password;	
	private String refreshToken;
}
