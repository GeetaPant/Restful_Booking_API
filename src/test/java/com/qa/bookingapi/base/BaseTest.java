package com.qa.bookingapi.base;

import org.testng.annotations.BeforeMethod;

import com.qa.bookingapi.client.RestClient;

public class BaseTest {
	
	protected final static String BASE_URL = "https://restful-booker.herokuapp.com";
	protected final static String TOKEN_URL = "https://restful-booker.herokuapp.com/auth";
	protected final static String URL_ENDPOINT = "/booking/";
	
	
	protected RestClient restClient;
	
	@BeforeMethod()
	public void setup() {
		 restClient = new RestClient();
	}

}
