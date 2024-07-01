package logging;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class LoggingExample {

	@Test(priority = 1)
	void logging() {
		given().when().get("https://reqres.in/api/users?page=2").then()
				// .log().body();
				// .log().cookies();
				.log().headers();
	}
}
