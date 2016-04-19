package br.fa7.spring.exercicios;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import br.fa7.spring.exercicios.resources.EmployeeResource;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(EmployeeResource.class);
	}
	
}
