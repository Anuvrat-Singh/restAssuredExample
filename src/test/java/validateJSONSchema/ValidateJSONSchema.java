package validateJSONSchema;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class ValidateJSONSchema {

	@Test(priority = 1)
	void validateJSONSchema1() {

		given().when().get("http://localhost:3000/store").then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("bookStoreJSONSchema.json"));
	}
}
