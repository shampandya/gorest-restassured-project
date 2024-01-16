package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostAssertionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public static void postsTest() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200)
        //1. Verify if the total record is 25
                .body("size()", equalTo(25))

        //2. Verify if the title of id = 93836 is equal to ”Textor barba arto decet vulgus valde acervus coaegresco rerum.”
                .body("find{it.id == 93836}.title", equalTo("Textor barba arto decet vulgus valde acervus coaegresco rerum."))

        //3. Check the single user_id in the Array list (5914064)
                .body("user_id", hasItem(5914064))

        //4. Check the multiple ids in the ArrayList (5914156, 5914070, 5914068)
                .body("user_id", hasItems("5914156", "5914070", "5914068"))

        //5. Verify the body of userid = 5914197
                .body("find{it.user_id == 5914065}.body", equalTo("Caritas vomer sublime. Tumultus nisi damno. Ulterius campana virgo. Argentum amaritudo comitatus. Temeritas theatrum cito. Chirographum adamo aqua. Canis adsidue fuga. Stipes canonicus accusamus. Clamo subiungo soleo. Video adeptio ater. Surculus modi delinquo. Tendo claustrum aestivus. Sulum ut vilitas. Ante vulgo depereo."));
    }
}

