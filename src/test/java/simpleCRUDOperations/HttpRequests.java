package simpleCRUDOperations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HttpRequests {

	int id = 0;

	@Test(priority = 1)
	void getUser() {
		given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2)).log()
				.all();
	}

//	@Test
//	void createUser() { 
//		HashMap payload = new HashMap();
//		payload.put("name", "Anuvrat");
//		payload.put("job", "SDET");
//
//		given().contentType("application/json").body(payload).when().post("https://reqres.in/api/users").then()
//				.statusCode(201).log().all();
//	}

	@Test(priority = 2)
	void createUserAndSaveId() {
		HashMap payload = new HashMap();
		payload.put("name", "Anuvrat");
		payload.put("job", "SDET");

		id = given().contentType("application/json").body(payload).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");
	}

	@Test(priority = 3, dependsOnMethods = { "createUserAndSaveId" })
	void updateUserUsingId() {
		HashMap payload = new HashMap();
		payload.put("name", "KumarSingh");
		payload.put("job", "Sr.SDET");

		given().contentType("application/json").body(payload).when().put("https://reqres.in/api/users/" + id).then()
				.statusCode(200).log().all();
	}

	@Test(priority = 4, dependsOnMethods = { "createUserAndSaveId" })
	void deleteUserUsingId() {

		given().when()

				.delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();
	}

}
