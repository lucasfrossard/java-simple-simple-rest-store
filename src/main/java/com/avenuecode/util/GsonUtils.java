package com.avenuecode.util;

import com.google.gson.Gson;

/**
 * Utilitary class for commong functions retalted to Gson library
 * @author lucasf
 *
 */
public class GsonUtils {
	
	/**
	 * Private constructor for this is a utilitary class
	 */
	private GsonUtils() {

	}

	public static <T> String convertToJson(T details) {
		Gson gson = new Gson();
		String json = gson.toJson(details);
		return json;
	}
}
