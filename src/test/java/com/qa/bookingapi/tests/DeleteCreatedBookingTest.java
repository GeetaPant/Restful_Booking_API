package com.qa.bookingapi.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.bookingapi.base.BaseTest;
import com.qa.bookingapi.constants.AuthTypes;
import com.qa.bookingapi.pojo.createBooking;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

	public class DeleteCreatedBookingTest extends BaseTest {
	
	//Create a new booking
	
		createBooking.BookingDates dates = new createBooking.BookingDates().builder()
										   .checkin("2025-11-20")
										   .checkout("2025-11-28")
										   .build();
			
		createBooking booking =			 createBooking.builder()
										.firstname("Samira")
										.lastname("Singh")
										.totalprice(64600)
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
			
			//Delete same booking
			Response responseDelete =restClient.delete(BASE_URL, URL_ENDPOINT+bookingID, AuthTypes.BEARER_TOKEN, ContentType.ANY);
			Assert.assertEquals(responseDelete.statusCode(), 201);
			
			//Fetch deleted booking
			Response responsedeleted = restClient.get(BASE_URL, URL_ENDPOINT+bookingID, AuthTypes.NO_AUTH, ContentType.JSON);
			Assert.assertEquals(responsedeleted.statusCode(), 404);
			//Assert.assertEquals(responsedeleted.body(), "Not Found");
			
		}	
}
