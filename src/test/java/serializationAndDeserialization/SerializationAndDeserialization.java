package serializationAndDeserialization;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationAndDeserialization {

	// serialization example

	@Test(priority = 1)
	void convertPOJOtoJson() throws JsonProcessingException {
		Student data_pojo = new Student();
		data_pojo.setName("Abc");
		data_pojo.setLocation("Noida");
		data_pojo.setPhone("123456");
		String courseArr[] = { "C", "C++" };
		data_pojo.setCourses(courseArr);

		ObjectMapper objMapper = new ObjectMapper();
		String pojoToJsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data_pojo);
		System.out.println(pojoToJsonData);
	}

	// deserialization example
	@Test(priority = 2)
	void convertJSONtoPojo() throws JsonProcessingException {
		String jsonToConvert = "{\n" + "  \"name\" : \"Abc\",\n" + "  \"location\" : \"Noida\",\n"
				+ "  \"phone\" : \"123456\",\n" + "  \"courses\" : [ \"C\", \"C++\" ]\n" + "}";

		ObjectMapper studentJson = new ObjectMapper();
		Student convertedPojo = studentJson.readValue(jsonToConvert, Student.class);

		System.out.println(convertedPojo.getName());
		System.out.println(convertedPojo.getCourses()[0]);
		System.out.println(convertedPojo.getCourses()[1]);
		System.out.println(convertedPojo.getLocation());

	}
}
