package playWithXMLResponse;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class ValidateXMLResponse {

	// http://restapi.adequateshop.com/api/Tourist?page=1

	@Test
	void xmlResponse() {
		// usual given, when ,then way

		given().when().get("http://restapi.adequateshop.com/api/Tourist?page=1").then().statusCode(200)
				.header("Content-Type", "application/xml; charset=utf-8").log().all();
	}
}
