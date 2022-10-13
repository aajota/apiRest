package br.com.wcaquino.rest;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserXMLTest {
	
	public static RequestSpecification reqSpec;
	public static ResponseSpecification resSpec;
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://restapi.wcaquino.me";
		//RestAssured.port = 443;
		//RestAssured.basePath = "";
		
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.log(LogDetail.ALL);
		reqSpec = reqBuilder.build();
		
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		resBuilder.expectStatusCode(200);
		resSpec = resBuilder.build();	
		
		RestAssured.requestSpecification = reqSpec;
		RestAssured.responseSpecification = resSpec;
		
	}

	@Test
	public void trabalharComXML() {	
		
		given()
		.when()
		     .get("/usersXML/3")
		.then()
	     .statusCode(200)
		
		     .rootPath("user")
		     .body("name", is("Ana Julia"))
		     .body("@id", is("3"))
		    
		     .rootPath("user.filhos")
		     .body("name.size()", is(2))
		     
		     .detachRootPath("filhos")
		     .body("filhos.name[0]", is("Zezinho"))
		     .body("filhos.name[1]", is("Luizinho"))
		     .body("filhos.name", hasItem("Zezinho"))
		     
		     .appendRootPath("filhos")
		     .body("name", hasItem("Luizinho"))
		     .body("name", hasItems("Luizinho", "Zezinho"))
		;
	}
	
	@Test
	public void pesquisasAvancadasComXML() {
		given()
		.when()
		     .get("/usersXML")
		.then()
		     .statusCode(200)
		     .body("users.user.size()", is(3))
//		     .body("users.user.findAll{it.age <= 25}.size()", is(2))
		     .body("users.user.@id", hasItems("1", "2", "3"))
		     .body("users.user.find{it.age == 25}.name", is("Maria Joaquina"))
//		     .body("users,user.findAll{it.name.toString().contains('n')}.name", hasItems("Maria Joaquina", "Ana Julia"))
//		     .body("users.user.salary.find{it != null}.toDouble()", is("1234.5678d"))
		     .body("users.user.age.collect{it.toInteger() *2}", hasItems(40, 50, 60))
//		     .body("user.user.namefindAll{it.toString().startWith('Maria)}.collect.it.toString().toUpperCase()}", is("MARIA JOAQUINA"))
		;
	
	}
	
	@Test
	public void pesquisasAvancadasComXMLeJava() {
		String nome = given()
		.when()
		     .get("/usersXML")
		.then()
		     .statusCode(200)
		     .extract().path("users.user.name.findAll{it.toString().startsWith('Maria')}")
		;
		Assert.assertEquals("Maria Joaquina".toUpperCase(), nome.toUpperCase());
		
		
}
	@Test
	public void pesquisasAvancadasComXPath() {
		given()
		.when()
		     .get("/usersXML")
		.then()
		     .statusCode(200)
		     .body(hasXPath("count(/users/user)",is("3")))
		     .body(hasXPath("/users/user[@id = '1']"))
		     .body(hasXPath("//user[@id = '2']"))
		     .body(hasXPath("//name[text() = 'Luizinho']/../../name", is("Ana Julia")))
		     .body(hasXPath("//name[text() = 'Ana Julia']/following-sibling::filhos", allOf(containsString("Zezinho"), containsString("Luizinho"))))
		     .body(hasXPath("/users/user/name", is("João da Silva")))
		     .body(hasXPath("//name", is("João da Silva")))
		     .body(hasXPath("/users/user[2]/name", is("Maria Joaquina")))
		     .body(hasXPath("/users/user[last()]/name", is("Ana Julia")))
		     .body(hasXPath("count(/users/user/name[contains(.,'n')])", is("2")))
		     .body(hasXPath("//user[age < 24]/name", is("Ana Julia")))
		     .body(hasXPath("//user[age > 20 and age < 30]/name", is("Maria Joaquina")))
		     .body(hasXPath("//user[age > 20] [age < 30]/name", is("Maria Joaquina")))

		     ;
	}
}