package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static String name = "Asha" + TestUtils.getRandomValue();
    static String email = "akakadiya@gmail.com" + TestUtils.getRandomValue();
    static String updatedEmail = "akakadiya123@gmail.com" + TestUtils.getRandomValue();
    static String gender = "Female";
    static String status = "Active";
    static int userId;
    //static String token;
    static String token = "9c866487ee622718ae3a4a253461f3db3de60afaa56f86e75ba03638103ed841";

    @Test
    public void test001() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                //.when()
                .body(userPojo)
                .when()
               // .post("/users");
        .post("/users").then().extract().response();
        response.then().statusCode(201);
        userId = response.jsonPath().get("id");
        System.out.println("Id is: "+ userId);
//        userId = response.then().contentType(ContentType.JSON).extract().path("user_id");
//        response.then().statusCode(201);
//        System.out.println("User Id number is : " + userId);
//        response.prettyPrint();
        //response.then().statusCode(201);

//        Response response = given()
//                .header("Authorization","Bearer" + token)
//                .contentType(ContentType.JSON)
//                .when()
//                .body(userPojo)
//                .post("/users");
//        response.then().log().all().statusCode(201);
//        userId = response.then().contentType(ContentType.JSON).extract().path("user_id");
//       // userId= response.body().jsonPath().getInt("id");
//        System.out.println(userId);
    }

    @Test
    public void test002() {
//        Response response = given()
//                .when()
//                .get("/public/v2/users" + userId);
//        response.then().statusCode(200);
//        response.prettyPrint();

        Response response = given()
                .header("Authorization","Bearer" + token)
                .when()
                .get("/users" + "/" + userId);
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test003() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(updatedEmail);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

//        Response response = given()
//                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
//                .when()
//                .body(userPojo)
//                .patch("/public/v2/users" + userId);
//        response.prettyPrint();
//        response.then().log().all().statusCode(200);

        Response response = given()
                .header("Authorization","Bearer" + token)
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .patch("/users" + "/" + userId);
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test004() {
//        Response response = given()
//                .pathParam("id", userId)
//                .when()
//                .delete("/public/v2/users/{id}");
//        response.prettyPrint();
//        response.then().log().all().statusCode(204);

        Response response = given()
                .header("Authorization","Bearer" + token)
                .when()
                .delete("/users" + "/" + userId);
        response.then().log().all().statusCode(204);
        response.prettyPrint();

    }

}
