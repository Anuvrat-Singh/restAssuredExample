package queryPathParams;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class QueryAndPathParams {

	@Test
	void queryAndPathParam() {
		// https://reqres.in/api/users?page=2&id=1
		given().pathParams("pathToVerify", "users") // path params
				.queryParam("page", 2).queryParam("id", 1).when().get("https://reqres.in/api/{pathToVerify}") // query
																												// params
																												// are
																												// already
																												// defined
																												// above
																												// so
																												// sent
																												// by
																												// default
				.then().statusCode(200).log().all();
	}

}
