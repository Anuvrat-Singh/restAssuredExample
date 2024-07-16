package diff_authentication_mech;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class BearerTokenAuth {

	@Test(priority = 1)
	void bearerTokenAuthExample() {

		String bearerToken = "<bearertoken>";
		given().headers("Authorization", "Bearer " + bearerToken).when().get("https://api.github.com/user/repos").then()
				.log().all();
	}
}
