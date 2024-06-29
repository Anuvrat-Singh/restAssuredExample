package diffWaysOfPOSTReq;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

public class DiffWaysOfPostReq {

	/*
	 * 1. Post req using hashmap
	 * 
	 * step1: run json-server for hosting an api. eg: students api step2: create a
	 * hashmap with the data that needs to be sent as a payload step3: make the
	 * request in given when then format step4: delete the request in parallel
	 * 
	 * 
	 */

	@Test(priority = 1)
	void testPOSTWithHashmap() {
		HashMap data = new HashMap();
		data.put("name", "Abc");
		data.put("location", "Noida");
		data.put("phone", "123456");
		String courseArr[] = { "C", "C++" };
		data.put("Courses", courseArr);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("Abc")).body("location", equalTo("Noida"))
				.body("phone", equalTo("123456")).body("Courses[0]", equalTo("C")).body("Courses[1]", equalTo("C++"))
				.header("Content-Type", "application/json").log().all();
	}

	@Test(priority = 2)
	void deleteCreatedRecord() {
		given().when().delete("http://localhost:3000/students/f3f1").then().statusCode(200).log().all();
	}

}
