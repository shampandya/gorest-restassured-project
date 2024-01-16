package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.gorest.utils.TestUtils.getRandomValue;
import static io.restassured.RestAssured.given;

public class UserCrudTest extends TestBase {

    static int userID;

    @Test
    public void test001() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName("aaa");
        userPojo.setEmail("abc" + getRandomValue() + "@gmail.com");
        userPojo.setGender("female");
        userPojo.setStatus("active");

        Response response = given()
                .header("Authorization", "Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .post("/users");
        response.prettyPrint();
        response.then().statusCode(201);

        userID = response.then().extract().path("id");
        System.out.println("Created User ID: " + userID);
    }

    @Test
    public void test002() {
        Response response = given()
                .header("Authorization", "Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Connection", "keep-alive")
                .when()
                .get("/users/" + userID);
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void test003() {
        UserPojo userPojo = new UserPojo();

        userPojo.setName("xxx");
        userPojo.setEmail(getRandomValue() + "@gmail.com");
        userPojo.setGender("female");
        userPojo.setStatus("active");
        Response response = given()
                .header("Authorization","Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Content-Type","application/json")
                .header("Connection","keep-alive")
                .when()
                .body(userPojo)
                .put("/users/" + userID);
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void test004() {
        Response response = given()
                .header("Authorization", "Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Connection", "keep-alive")
                .when()
                .delete("/users/" + userID);
        response.prettyPrint();
        response.then().statusCode(204);

        response = given()
                .header("Authorization", "Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Connection", "keep-alive")
                .when()
                .get("/users/" + userID);
        response.prettyPrint();
        response.then().statusCode(404);
    }
}
