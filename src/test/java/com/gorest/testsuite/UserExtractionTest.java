package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public static void userTest() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);

        List<Integer> userIDs = response.extract().path("id");
        System.out.println("1. Extract the All Ids = " + userIDs);

        List<String> userNames = response.extract().path("name");
        System.out.println("2. Extract the All names = " + userNames);

        String name = response.extract().path("[4].name");
        System.out.println("3. Extract the name of fifth object = " + name);

        List<String> inactiveName = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("4. Extract the names of all object whose status (inactive) = " + inactiveName);

        List<String> activeGender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("5. Extract the gender of all the object whose status (active) = " + activeGender);

        List<String> femaleName = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("6. Print the names of the object whose gender (female) = " + femaleName);

        List<String> inactiveEmail = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("7. Get all the emails of the object where status (inactive) = " + inactiveEmail);

        List<Integer> maleIds = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("8. Get the ids of the object where gender (male) = " + maleIds);

        List<String> status = response.extract().path("status");
        System.out.println("9. Get all the status = " + status);

        String email = response.extract().path("find{it.email == 'embranthiri_akshat@pfeffer.test'}.name");
        System.out.println("10. Get email of the object where name (Akshat Embranthiri) = " + email);

        String gender = response.extract().path("find{it.id == 5914057}.gender");
        System.out.println("11. Get gender of id (5914057) = " + gender);
    }
}
