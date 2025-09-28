package com.qa.bookingapi.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.bookingapi.base.BaseTest;
import com.qa.bookingapi.constants.AuthTypes;
import com.qa.bookingapi.pojo.createBooking;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class createBookingTest extends BaseTest{
	
	createBooking.BookingDates dates = new createBooking.BookingDates().builder()
										.checkin("2025-11-20")
									   .checkout("2025-11-28")
									   .build();
									
	createBooking booking = createBooking.builder()
							.firstname("Vyom")
							.lastname("Pant")
							.totalprice(5825)
							.depositpaid(false)
							.bookingdates(dates)
							.additionalneeds("Breakfast")
							.build();
							
							
	@Test
	public void createNewBookingTest() {
		
	Response response = restClient.post(BASE_URL, URL_ENDPOINT, booking, AuthTypes.NO_AUTH, ContentType.JSON);
	int bookingid = response.jsonPath().get("bookingid");
	System.out.println("Created Booking id is ---"+bookingid);
	
	Assert.assertEquals(response.jsonPath().getString("booking.firstname"), booking.getFirstname());
	Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"), booking.getBookingdates().getCheckin());
	Assert.assertEquals(response.statusCode(), 200);
	}

}
