package diff_authentication_mech;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class ApiKeyAuthMech {
	@Test(priority = 1)
	void apiKeyAuth() {
		given().queryParam("key", "value")

				.when().get("url").then().statusCode(200).log().all();
	}
}
