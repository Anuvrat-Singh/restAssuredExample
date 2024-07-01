package playWithCookies;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class CookiesAndHeaders {

	@Test(priority = 1)
	void cookiesAndHeaders() {
		Response res = given().when().get("https://www.google.com");
		// .then().cookie("AEC", "1").log().all();

		// get single cookie info
		String cookie_value = res.getCookie("AEC");
		System.out.println("value = " + cookie_value);

		// get all cookie info
		Map<String, String> cookies_values = res.getCookies();

		System.out.println(cookies_values.keySet());

		for (String k : cookies_values.keySet()) {
			String cookie_val = res.getCookie(k);
			System.out.println(k + "		" + cookie_val);

		}
	}

	@Test(priority = 2)
	void validateHeaders() {
		Response res = given().when().get("https://www.google.com");

		String HeaderVal = res.getHeader("Content-Type");
		System.out.println("Content-Type is: " + HeaderVal);

		// get all headers

		Headers headers = res.getHeaders();

		System.out.println("----------------------------");

		for (Header hd : headers) {
			System.out.println(hd.getName() + "		" + hd.getValue());
		}

	}
}
