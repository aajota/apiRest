package br.com.wcaquino.rest;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;

public class EnvioDadosTest {
	
	@Test
	public void enviarValorViaQuerty() {
		given()
		   .log().all()
		.when()
		   .get("https://restapi.wcaquino.me/v2/users?format=json")
		.then()
		   .log().all()
		   .statusCode(200)
		   .contentType(ContentType.JSON)
		;
	}

	@Test
	public void enviarValorViaParam() {
		given()
		   .log().all()
		   .queryParam("format", "xml")
		   .queryParam("outra", "coisa")
		.when()
		   .get("https://restapi.wcaquino.me/v2/users")
		.then()
		   .log().all()
		   .statusCode(200)
		   .contentType(ContentType.XML)
		   .contentType(Matchers.containsString("utf-8"))
		;
	}
	
	@Test
	public void enviarValorViaQuertyViaParam() {
		given()
		   .log().all()
		   .queryParam("format", "xml")
		   .queryParam("outra", "coisa")
		.when()
		   .get("https://restapi.wcaquino.me/v2/users")
		.then()
		   .log().all()
		   .statusCode(200)
		   .contentType(ContentType.XML)
		   .contentType(Matchers.containsString("utf-8"))
		;
	}
	
	@Test
	public void enviarValorViaHeader() {
		given()
		   .log().all()
		   .accept(ContentType.XML)
		.when()
		   .get("https://restapi.wcaquino.me/v2/users")
		.then()
		   .log().all()
		   .statusCode(200)
		   .contentType(ContentType.XML) 
		;
	}
}
