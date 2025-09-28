package com.qa.bookingapi.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(Include.NON_NULL)
public class createBooking {

	private Integer bookingid;
	private String firstname;
	private String lastname;
	private Number totalprice;
	private Boolean depositpaid;
	private String additionalneeds;
	private BookingDates bookingdates;
	
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class BookingDates{
		private String checkin;
		private String checkout;
	}

}
