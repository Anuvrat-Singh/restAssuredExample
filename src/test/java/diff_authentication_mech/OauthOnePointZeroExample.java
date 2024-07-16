package diff_authentication_mech;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class OauthOnePointZeroExample {
	@Test(priority = 1)
	void OAuth1() {
		String bearerToken = "<bearertoken>";
		given().auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken").when()
				.get("https://api.github.com/user/repos").then().log().all();
	}
}
