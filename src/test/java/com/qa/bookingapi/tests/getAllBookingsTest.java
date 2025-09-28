package com.qa.bookingapi.tests;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.bookingapi.base.BaseTest;
import com.qa.bookingapi.constants.AuthTypes;
import com.qa.bookingapi.pojo.createBooking;
import com.qa.bookingapi.utils.jsonUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class getAllBookingsTest extends BaseTest{


	@Test
	public void getAllBookingsIDTest() {
		Response response = restClient.get(BASE_URL, URL_ENDPOINT, AuthTypes.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
		createBooking[] book = jsonUtils.deserializeResponse(response, createBooking[].class);
	//	System.out.println(Arrays.toString(book));
		System.out.println(book.length);
		
		for(createBooking b : book) {
			System.out.println("ID is : "+b.getBookingid());
		
			System.out.println("****************");
	}

	}
}
