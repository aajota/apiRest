package br.com.wcaquino.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

public class FileTest {
	
	@Test
	public void obrigarEnvioAquivo() {
		given()
		   .log().all()
		.when()
		   .post("http://restapi.wcaquino.me/upload")
		.then()
		   .log().all()
		   .statusCode(404)
		   .body("error", is("Arquivo não enviado"))
		   
		;
	}
	@Test
	public void fazerUploadDoAquivo() {
		given()
		   .log().all()
		   .multiPart("arquivo", new File("src/main/resources/Error.pdf"))
		.when()
		   .post("http://restapi.wcaquino.me/upload")
		.then()
		   .log().all()
		   .statusCode(200)
		   .body("name", is("Error.pdf"), "md5", is("094cc139665ce06321e980a9df3ad5e9"), "size", is(30494))	   
		;
	}
	
	@Test
	public void naoDeveFazerUploadDeAquivoGrande() {
		given()
		   .log().all()
		   .multiPart("arquivo", new File("src/main/resources/foto.jpg"))
		.when()
		   .post("http://restapi.wcaquino.me/upload")
		.then()
		   .log().all()
		   .time(lessThan(4000L))
		   .statusCode(413)
		;
	}
	
	@Test
	public void deveBaixarArquivo() throws IOException {
		byte[] image = given()
		   .log().all()
		.when()
		   .get("http://restapi.wcaquino.me/download")
		.then()
	//	   .log().all()
		   .statusCode(200)
		   .extract().asByteArray()
		;
		
		File imagem = new File("src/main/resources/file.jpg");
		OutputStream out = new FileOutputStream(imagem);
		out.write(image);
		out.close();

		Assert.assertThat(imagem.length(), lessThan(100000L));
	}
	
	
}
