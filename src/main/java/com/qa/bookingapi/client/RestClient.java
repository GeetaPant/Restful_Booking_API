package com.qa.bookingapi.client;
import com.qa.bookingapi.manager.configManagers;
import com.qa.bookingapi.pojo.Credentials;
import com.qa.bookingapi.constants.AuthTypes;
import com.qa.bookingapi.exceptions.FrameworkException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.expect;

public class RestClient {
	
	private ResponseSpecification response200 = expect().statusCode(200);
	private ResponseSpecification response201 = expect().statusCode(201);
	private ResponseSpecification responseSpec200or404 = expect().statusCode(anyOf(equalTo(200), equalTo(404)));
	
	
	private RequestSpecification setupRequest(String baseUrl, AuthTypes authType, ContentType contentType) {
		
		RequestSpecification request = RestAssured.
												given().log().all()
													.baseUri(baseUrl)
														.contentType(contentType);
		switch(authType) {
		case BEARER_TOKEN:{
			request.header("Cookie", "token="+generateToken());
			break;
		}
		case NO_AUTH:{
			System.out.println("No Authentication required");
			break;
		}
		default:
			System.out.println("This auth is not required.... Please pass the right auth type");
			throw new FrameworkException("AUTH NOT SUPPORTED");
	}
		return request;
	}
	private String generateToken() {
		
		Credentials cred = Credentials.builder()
				.username(configManagers.get("Username"))
				.password(configManagers.get("Password"))
				.build();
		
		return RestAssured.given()
			.contentType(ContentType.JSON)
			.body(cred)
			.when().log().all()
			.post(configManagers.get("token_url"))
			.then()
			.extract()
			.path("token");
	}
	
	//***************************CRUD OPERATIONS****************************************
	
	public Response get(String baseUrl, String endPoint, AuthTypes authType, ContentType contentType) {
		
		RequestSpecification request= setupRequest(baseUrl, authType, contentType);
		Response response = request.get(endPoint).then().spec(responseSpec200or404).extract().response();
		response.prettyPrint();
		return response;
	}
	
	public <T>Response post(String baseUrl, String endPoint, T body, AuthTypes authType, ContentType contentType) {
		
		RequestSpecification request = setupRequest(baseUrl, authType,contentType);
		Response response=	request.body(body).post(endPoint).then().spec(response200).extract().response();
		response.prettyPrint();
		return response;
	}

	public <T>Response put(String baseUrl, String endPoint, T body, AuthTypes authType, ContentType contentType) {
		
		RequestSpecification request = setupRequest(baseUrl, authType,contentType);
		Response response=	request.body(body).put(endPoint).then().spec(response200).extract().response();
		response.prettyPrint();
		return response;
	}
public <T>Response patch(String baseUrl, String endPoint, T body, AuthTypes authType, ContentType contentType) {
		
		RequestSpecification request = setupRequest(baseUrl, authType,contentType);
		Response response=	request.body(body).patch(endPoint).then().log().all().spec(response200).extract().response();
		response.prettyPrint();
		return response;
	}
public Response delete(String baseUrl, String endPoint, AuthTypes authType, ContentType contentType) {
	
	RequestSpecification request= setupRequest(baseUrl, authType, contentType);
	Response response = request.delete(endPoint).then().spec(response201).extract().response();
	response.prettyPrint();
	return response;
}
	
}
