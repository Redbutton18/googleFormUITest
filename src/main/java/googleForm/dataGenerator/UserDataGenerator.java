package googleForm.dataGenerator;

import com.github.javafaker.Faker;

public class UserDataGenerator {

    private static Faker faker = new Faker();

    public static String getFakerFirstName() {
        return faker.name().firstName();
    }

    public static String getFakerEmailAddress() {
        return faker.name().firstName() + faker.internet().emailAddress();
    }
}
