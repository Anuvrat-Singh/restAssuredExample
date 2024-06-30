package diffWaysOfPOSTReq;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class DiffWaysOfPostReq {

	/*
	 * 1. Post req using hashmap.
	 * 
	 * step1: run json-server for hosting an api. eg: students api. step2: create a
	 * hashmap with the data that needs to be sent as a payload. step3: make the
	 * request in given when then format. step4: delete the request in parallel.
	 */

	// @Test(priority = 1)
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

	/*
	 * 2. Post request body using org.json. step1: run json-server for hosting an
	 * api. eg: students api. step2: create a jsonObject with the data that needs to
	 * be sent as a payload. Convert the json object to string format step3: make
	 * the request in given when then format. step4: delete the request in parallel.
	 */

	// @Test(priority = 2)
	void testPOSTWithJsonBody() {

		JSONObject data = new JSONObject();
		data.put("name", "Abc");
		data.put("location", "Noida");
		data.put("phone", "123456");
		String courseArr[] = { "C", "C++" };
		data.put("Courses", courseArr);

		given().contentType("application/json").body(data.toString()).when().post("http://localhost:3000/students")
				.then().statusCode(201).body("name", equalTo("Abc")).body("location", equalTo("Noida"))
				.body("phone", equalTo("123456")).body("Courses[0]", equalTo("C")).body("Courses[1]", equalTo("C++"))
				.header("Content-Type", "application/json").log().all();

	}

	/*
	 * Third Approach: By using POJO Classes
	 */
	// @Test(priority = 3)
	void testPOSTwithPOJO() {

		pojo_POSTReq data = new pojo_POSTReq();
		data.setName("Abc");
		data.setLocation("Noida");
		data.setPhone("123456");
		String courseArr[] = { "C", "C++" };
		data.setCourses(courseArr);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("Abc")).body("location", equalTo("Noida"))
				.body("phone", equalTo("123456")).body("courses[0]", equalTo("C")).body("courses[1]", equalTo("C++"))
				.header("Content-Type", "application/json").log().all();

	}

	// 4th Approach: Using External json file

	@Test(priority = 1)
	void testPOSTWithExternalJSONFile() throws FileNotFoundException {

		File f = new File("./body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);

		given().contentType("application/json").body(data.toString()).when().post("http://localhost:3000/students")
				.then().statusCode(201).body("name", equalTo("Abcd")).body("location", equalTo("Noida"))
				.body("phone", equalTo("123456")).body("courses[0]", equalTo("Java")).body("courses[1]", equalTo("C#"))
				.header("Content-Type", "application/json").log().all();
	}

	@Test(priority = 2)
	void deleteCreatedRecord() {
		given().when().delete("http://localhost:3000/students/f518").then().statusCode(200).log().all();
	}

}
