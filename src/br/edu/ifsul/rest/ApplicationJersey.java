package br.edu.ifsul.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jettison.JettisonFeature;


/*
 * Configura o Jersey.
 */
public class ApplicationJersey extends Application{
	
	//escaneia as anotações Jersey
	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put("jersey.config.server.provider.packages", "br.edu.ifsul");
		return properties;
	}
	
	
	//adiciona uma feature ao Jersey
	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();
		//adiciona o objeto que converte objeto Java em JSON
		singletons.add(new JettisonFeature());
		return singletons;
	}
	
	//adiciona a feature para segurança por anotações
//	@Override
//	public Set<Class<?>> getClasses() {
//		Set<Class<?>> s = new HashSet<>();
//		//Segurança por anotações, segundo a especificação JSR-250.
//		s.add(RolesAllowedDynamicFeature.class);
//		return s;
//	}
}
