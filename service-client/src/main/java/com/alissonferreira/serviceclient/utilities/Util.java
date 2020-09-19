package com.alissonferreira.serviceclient.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
	
	private Util() { }
	
	public static String asJsonString(Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
