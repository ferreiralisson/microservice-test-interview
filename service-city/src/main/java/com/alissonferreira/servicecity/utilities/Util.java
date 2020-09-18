package com.alissonferreira.servicecity.utilities;

import java.util.List;

public class Util {
	
	private Util() { }
	
	public static boolean isNotEmptyList(List<?> listOfGenerics) {
		return listOfGenerics != null && !listOfGenerics.isEmpty();
	}

}
