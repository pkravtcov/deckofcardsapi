package com.deckofcardsapi.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetTest extends RootTest {
	
	//each test should be independent from each other
	//and test one particular feature at a time
		
	@Test
	public void createANewDeckOfCards() {
		Response newDeckResponse = httpRequest.get(NEW_DECK_RESOURCE);
		JsonPath jsonPathEvaluator = newDeckResponse.jsonPath();
		assertEquals(true, jsonPathEvaluator.get("success"));
		assertTrue(StringUtils.isNotBlank((String)jsonPathEvaluator.get("deck_id")));
		assertEquals(new Integer(52), jsonPathEvaluator.get("remaining"));
		assertEquals(false, jsonPathEvaluator.get("shuffled"));
	}
	
	@Test
	public void drawOneCardFromDeck() {
		Response newDeckResponse = httpRequest.get(NEW_DECK_RESOURCE);
		JsonPath jsonPathEvaluator = newDeckResponse.jsonPath();
		String deckId = (String)jsonPathEvaluator.get("deck_id");
		Response drawResponse = httpRequest.get("/deck/" + deckId + "/draw/");
		jsonPathEvaluator = drawResponse.jsonPath();
		assertEquals(new Integer(51), jsonPathEvaluator.get("remaining"));
	}
	
	@Test
	public void drawTwoCardsFromDeck() {
		Response newDeckResponse = httpRequest.get(NEW_DECK_RESOURCE);
		JsonPath jsonPathEvaluator = newDeckResponse.jsonPath();
		String deckId = (String)jsonPathEvaluator.get("deck_id");
		Response drawResponse = httpRequest.get("/deck/" + deckId + "/draw/?count=2");
		jsonPathEvaluator = drawResponse.jsonPath();
		assertEquals(new Integer(50), jsonPathEvaluator.get("remaining"));
	}

}
