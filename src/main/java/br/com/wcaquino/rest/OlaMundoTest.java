/*package br.com.wcaquino.rest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	
	@Test
	public void testOlaMundo() {
		Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue(response.statusCode() == 200);
		Assert.assertTrue("O status code deve ser 200", response.statusCode() == 200);
		Assert.assertEquals(response.statusCode(), 200);
				
	    ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}
	
	@Test
	public void outrasFormaDeRestAssured() {
		Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");	
	    ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
		
		get("http://restapi.wcaquino.me/ola").then().statusCode(200);
		
		given()//pr� condi��es
		.when()//a��o
		.get("http://restapi.wcaquino.me/ola")
		.then()//assertivas
		.statusCode(200);
		
	}
	@Test
	public void conhecendoHamcrest() {
		Assert.assertThat("Maria", Matchers.is("Maria"));
		Assert.assertThat("128", Matchers.is("128"));
		Assert.assertThat("128", Matchers.isA(Integer.class));
		Assert.assertThat("128d", Matchers.isA(Double.class));
		Assert.assertEquals("128d", Matchers.greaterThan(130d));
		Assert.assertEquals("128d", Matchers.lessThan(130d));
		
		List<Integer> impares = Arrays.asList(1,3,5,7,9);
		assertThat(impares, hasSize(5));
		assertThat(impares, contains(1,3,5,7,9));
		assertThat(impares, containsInAnyOrder(1,3,5,7,9));
		assertThat(impares, hasItem(9));
		
		assertThat("Maria", is(not("Jo�o")));
		assertThat("Maria", (not("Jo�o"));
		assertThat("Joaquina", anyOff(is("maria"), is("Joaquina")));
		assertThat("Joaquina", allOff(startsWith("maria"), endWith("toto"), containsString("qui")));
		
		
	}
	@Test
	public void validarBody() {
	given()
	.when()
	.get("http://restapi.wcaquino.me/ola")
	.then()
	.statusCode(200)
	.body(Matchers.is("Ola Mundo!"))
	.body(containsString("Mundo"));
	}
	
	}
*/

