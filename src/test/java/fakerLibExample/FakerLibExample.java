package fakerLibExample;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerLibExample {

	@Test(priority = 1)
	void fakerDummyDataGeneration() {

		Faker faker = new Faker();
		String fullname = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();

		String username = faker.name().username();
		String password = faker.internet().password();

		String number = faker.phoneNumber().cellPhone();
		String email = faker.internet().emailAddress();

		System.out.println("Firstname: " + firstName);
		System.out.println("Lastname: " + lastName);
		System.out.println("Fullname: " + fullname);

		System.out.println("username: " + username);
		System.out.println("Password: " + password);

		System.out.println("number: " + number);
		System.out.println("email: " + email);

	}

}
