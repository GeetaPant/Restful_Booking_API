package com.qa.bookingapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class jsonUtils {

	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static <T> T deserializeResponse(Response response, Class<T> targetClass) {
		try 
		{
			return mapper.readValue(response.getBody().asString(), targetClass);
		}  
		catch (Exception e) {
			throw new RuntimeException("Deserialization failed" +targetClass.getName());
		}
	}
}
