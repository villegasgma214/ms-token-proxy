package mx.com.gentera.ms.tokenproxy.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDTO implements Serializable {

	private static final long serialVersionUID = -6041277665644177964L;
	private Long id;
	private String name;
	private String name2;
	private String name3;
	private String patern;
	private String matern;
	private Integer idNationality;
}
