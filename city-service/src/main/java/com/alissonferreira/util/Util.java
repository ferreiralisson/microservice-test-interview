package com.alissonferreira.util;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	private Util() {
	}

	public static boolean isNotEmptyList(List<?> listOfGenerics) {
		return listOfGenerics != null && !listOfGenerics.isEmpty();
	}

	public static String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
