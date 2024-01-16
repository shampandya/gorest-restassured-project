package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class UserAssertionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public static void userTest() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200)

                //1. Verify if the total record is 20
                .body("size()", equalTo(20))

                //2. Verify if the name of id = 5914074 is equal to ”Rev. Brahmaanand Khanna”
                .body("find{it.id == 5914074}.name", equalTo("Rev. Brahmaanand Khanna"))

                //3. Check the single ‘Name’ in the Array list (Bhargavi Shah JD)
                .body("name", hasItem("Bhargavi Shah JD"))

                //4. Check the multiple ‘Names’ in the ArrayList (Bhargavi Shah JD, Abhaya Mahajan I, Tara Panicker)
                .body("name", hasItems("Bhargavi Shah JD", "Abhaya Mahajan I", "Tara Panicker"))

                //5. Verify the emai of userid = 5914060 is equal “ramaa_banerjee@roob.example”
                .body("find{it.id == 5914060}.email", equalTo("ramaa_banerjee@roob.example"))

                //6. Verify the status is “inactive” of user name is “Deeptendu Menon”
                .body("find{it.name == 'Deeptendu Menon'}.status", equalTo("inactive"))

                //7. Verify the Gender = female of user name is “Manik Gill”
                .body("find{it.name == 'Manik Gill'}.gender", equalTo("female"));
    }
}
