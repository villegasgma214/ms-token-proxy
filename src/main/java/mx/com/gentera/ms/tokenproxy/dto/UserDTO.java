package mx.com.gentera.ms.tokenproxy.dto;

import java.io.Serializable;
import java.util.List;

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
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1302919405438208292L;
	private String name;
	private List<String> roles;
	private PersonDTO person;
	private String languaje;
	private String activeRol;
	private JwtDto jwt;
}
