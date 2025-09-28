package com.qa.bookingapi.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.bookingapi.base.BaseTest;
import com.qa.bookingapi.constants.AuthTypes;
import com.qa.bookingapi.manager.configManagers;
import com.qa.bookingapi.pojo.Credentials;
import com.qa.bookingapi.pojo.createBooking;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateBookingTest extends BaseTest {

//	Credentials cred = Credentials.builder()
//						.username(configManagers.get("Username"))
//						.password(configManagers.get("Password"))
//						.build();
//	@BeforeMethod
//	public void generateToken() {
//	Response response = restClient.post(BASE_URL, TOKEN_URL, cred, AuthTypes.NO_AUTH, ContentType.JSON);
//	String TokenID = response.jsonPath().getString("token");
//	 System.out.println("TokenId --" +TokenID);
//	 configManagers.set("bearerToken", TokenID);
//	}
//	
	
	//Create a new booking
	createBooking.BookingDates dates = new createBooking.BookingDates().builder()
									   .checkin("2025-11-20")
									   .checkout("2025-11-28")
									   .build();
		
	createBooking booking =			 createBooking.builder()
									.firstname("Vedant")
									.lastname("Pant")
									.totalprice(60000)
								    .depositpaid(true)
								    .bookingdates(dates)
								    .additionalneeds("Breakfast")
								    .build();
	
	@Test
	public void createNewBooking() {
		Response responsePost = restClient.post(BASE_URL, URL_ENDPOINT, booking, AuthTypes.NO_AUTH, ContentType.JSON);
		 int bookingID = responsePost.jsonPath().get("bookingid");
		 System.out.println("Created Booking Id is----" +bookingID);
		 Assert.assertEquals(responsePost.statusCode(), 200);		
	
	//Get the same booking ID
		Response responseGet = restClient.get(BASE_URL, URL_ENDPOINT+bookingID, AuthTypes.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(responseGet.statusCode(), 200);
		
 //Update same Booking ID
		booking.setLastname("Sharma");
		dates.setCheckin("2025-11-01");
		dates.setCheckout("2025-11-06");
		
		Response responsePatch = restClient.patch(BASE_URL, URL_ENDPOINT+bookingID, booking, AuthTypes.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responsePatch.statusCode(), 200);
	}
	
}
