package mx.com.gentera.ms.tokenproxy.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());             
    }    

    @SuppressWarnings("rawtypes")
	private ApiInfo apiInfo() {
    	Collection<VendorExtension> vendors = new ArrayList<VendorExtension>();
    	VendorExtension vendor = new ObjectVendorExtension("gentera");
    	vendors.add(vendor);
        ApiInfo apiInfo = new ApiInfo(
                "ms-token-proxy REST API", 
                "Servicios del modulo de administracion de sistema", 
                "1.0",
                "Terminos del servicio",
                new Contact("Gentera", "http://www.gentera.com.mx/", "contacto@gentera.com"),
                "Apache License Version 2.0", 
                "https://www.apache.org/licenses/LICENSE-2.0",
                vendors);
        return apiInfo;
    }  
}