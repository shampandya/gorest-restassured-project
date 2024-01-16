package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostExtractionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public static void postsTest() {
        response = given().queryParam("page", 1).queryParam("per_page", 25).when().get("/posts").then().statusCode(200);

        //1. Extract the title
        List<String> title = response.extract().path("title");
        System.out.println("Extract the title = " + title);

        //2.Extract the total number of record
        int totalNoOfRecords = response.extract().path("size()");
        System.out.println("2. Extract the total number of record = " + totalNoOfRecords);

        //3. Extract the body of 15 th record
        String record = response.extract().path("[15].body");
        System.out.println("3. Extract the body of 15 th record = " + record);

        //4. Extract the user_id of all the records
        List<Integer> userId = response.extract().path("user_id");
        System.out.println("4. Extract the user_id of all the records = " + userId);

        //5. Extract the title of all the records
        List<String> records = response.extract().path("title");
        System.out.println("5. Extract the title of all the records = " + records);

        //6. Extract the title of all records whose user_id = 5914071
        List<String> titleOfIds = response.extract().path("findAll{it.user_id == 5914071}.title");
        System.out.println("6. Extract the title of all records whose user_id (5914071) = " + titleOfIds);

        //7. Extract the body of all records whose id = 93942
        List<String> body = response.extract().path("findAll{it.id == 93942}.body");
        System.out.println("7. Extract the body of all records whose id (93942) = " + body);
    }
}
