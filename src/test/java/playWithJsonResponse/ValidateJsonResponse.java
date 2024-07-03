package playWithJsonResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ValidateJsonResponse {

	// 1st approach, the usual suspect

	// @Test(priority = 1)
	void testJsonResponse() {

		given().contentType(ContentType.JSON).when().get("http://localhost:3000/store").then().statusCode(200)
				.header("Content-Type", "application/json").body("books[1].author", equalTo("Evelyn Waugh"));
	}

	// second approach ----> testng assertions on response by storing response as a
	// variable
	// @Test(priority = 2)
	void testJsonResponse2() {
		Response res = given().contentType(ContentType.JSON).when().get("http://localhost:3000/store");

		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals("Content-type", "application/json");
		String author = res.jsonPath().get("books[1].author").toString();
		Assert.assertEquals(author, "Evelyn Waugh");
	}

	// Traversing through the json and searching via JSONObject class
	@Test(priority = 3)
	void testJsonResponseViaJSONObjectClass() {
		Response res = given().contentType(ContentType.JSON).when().get("http://localhost:3000/store");

		boolean status = false;
		JSONObject jo = new JSONObject(res.asString());
//		for (int i = 0; i < jo.getJSONArray("books").length(); i++) {
//			String title = jo.getJSONArray("books").getJSONObject(i).get("title").toString();
//			System.out.println(title);
//		}

		for (int i = 0; i < jo.getJSONArray("books").length(); i++) {
			String title = jo.getJSONArray("books").getJSONObject(i).get("title").toString();
			if (title.equals("Moby Dick")) {
				status = true;
				break;
			}
		}

		Assert.assertEquals(status, true);

	}

}
