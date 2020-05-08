package com.deckofcardsapi.integration;

import org.junit.Before;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RootTest {
	protected RequestSpecification httpRequest;
	public static final String NEW_DECK_RESOURCE = "/deck/new/";
	
	@Before
	public void setup() {
		RestAssured.baseURI = "https://deckofcardsapi.com/api";
		httpRequest = RestAssured.given();
	}

}
