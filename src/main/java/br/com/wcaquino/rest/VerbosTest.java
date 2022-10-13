package br.com.wcaquino.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class VerbosTest {
	@Test
	public void salvarUsuario() {
		given()
		    .log().all()
		    .contentType("application/json")
		    .body("	{\"name\": \"Jose\",\"age\": 32}")
		.when()
		    .post("https://restapi.wcaquino.me/users")
		.then()
		    .log().all()
		    .statusCode(201)
		    .body("id", is(notNullValue()))
		    .body("name", is("Jose"))
		    .body("age", is(32))
		;
	}
	
	@Test
	public void naoDeveSalvarUsuarioSemNome() {
		given()
	    .log().all()
	    .contentType("application/json")
	    .body("{\"age\": 32}")
	.when()
	    .post("https://restapi.wcaquino.me/users")
	.then()
	    .log().all()
	    .statusCode(400)
	    .body("id", is(nullValue()))
	    .body("error", is("Name é um atributo obrigatório"))
	;
		
	}
	@Test
	public void salvarUsuarioViaXML() {
		given()
		    .log().all()
		    .contentType("application/XML")
		    .body("<user><name>Jose</name><age>32</age></user>")
		.when()
		    .post("https://restapi.wcaquino.me/usersXML")
		.then()
		    .log().all()
		    .statusCode(201)
		    .body("user.@id", is(notNullValue()))
		    .body("user.name", is("Jose"))
		    .body("user.age", is("32"))
		;
	}
	@Test
	public void alterarUsuario() {
		given()
		    .log().all()
		    .contentType("application/json")
		    .body("	{\"name\": \"Usuario alterado\",\"age\": 80}")
		.when()
		    .put("https://restapi.wcaquino.me/users/1")
		.then()
		    .log().all()
		    .statusCode(200)
		    .body("id", is(1))
		    .body("name", is("Usuario alterado"))
		    .body("age", is(80))
		    .body("salary", is(1234.5678F))
		;
	}
	@Test
	public void costumizarURL() {
		given()
		    .log().all()
		    .contentType("application/json")
		    .body("	{\"name\": \"Usuario alterado\",\"age\": 80}")
		.when()
		    .put("https://restapi.wcaquino.me/{entidade}/{userId}", "users", "1")
		.then()
		    .log().all()
		    .statusCode(200)
		    .body("id", is(1))
		    .body("name", is("Usuario alterado"))
		    .body("age", is(80))
		    .body("salary", is(1234.5678F))
		;
	}
	@Test
	public void costumizarURLparte2() {
		given()
		    .log().all()
		    .contentType("application/json")
		    .body("	{\"name\": \"Usuario alterado\",\"age\": 80}")
		    .pathParam("entidade", "users")
		    .pathParam("userId", 1)
		.when()
		    .put("https://restapi.wcaquino.me/{entidade}/{userId}")
		.then()
		    .log().all()
		    .statusCode(200)
		    .body("id", is(1))
		    .body("name", is("Usuario alterado"))
		    .body("age", is(80))
		    .body("salary", is(1234.5678F))
		;
	}
	@Test
	public void removerUsuario() {
	given()
	  .log().all()
	 
	.when()
	  .delete("https://restapi.wcaquino.me/users/1")
	.then()
	  .log().all()
	  .statusCode(204)

	;
	
	}
	@Test
	public void naoDeveRemoverUsuarioInexistente() {
	given()
	  .log().all()
	 
	.when()
	  .delete("https://restapi.wcaquino.me/users/1000")
	.then()
	  .log().all()
	  .statusCode(400)
	  .body("error", is("Registro inexistente"))

	;
	
	}
	@Test
	public void salvarUsuarioUsandoMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name","Usuario via map");
		params.put("age", 25);
		
		given()
		    .log().all()
		    .contentType("application/json")
		    .body(params)
		.when()
		    .post("https://restapi.wcaquino.me/users")
		.then()
		    .log().all()
		    .statusCode(201)
		    .body("id", is(notNullValue()))
		    .body("name", is("Usuario via map"))
		    .body("age", is(25))
		;
	}
	}




