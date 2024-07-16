package diff_authentication_mech;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class OauthTwoExample {
	@Test(priority = 1)
	void OAuth2() {
		given().auth().oauth2("OAuth2Token").when().get("https://api.github.com/user/repos").then().log().all();
	}
}
