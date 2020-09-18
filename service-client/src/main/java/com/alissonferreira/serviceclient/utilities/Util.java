package com.alissonferreira.serviceclient.utilities;

import org.springframework.web.client.RestTemplate;

public class Util {
	
	private Util() { }
	
	public static Object getRestTamplate(String url, Class<?> typeClassGeneric) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, typeClassGeneric.getClass());
	}

}
