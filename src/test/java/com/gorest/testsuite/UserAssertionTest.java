package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * 1. Verify the if the total record is 20
 * 2. Verify the if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
 * 3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
 * 4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
 * 5. Verify the emai of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
 * 6. Verify the status is “Active” of user name is “Amaresh Rana”
 * 7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”
 */
public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void start() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size()", equalTo(20));
    }

    //2. Verify the if the name of id = 5914139 is equal to ”tara panicker”
    @Test
    public void test002() {
        response.body("find{it.id == 5914062}.name", equalTo("Tara Panicker"));
    }

    //3. Check the single ‘Name’ in the Array list (ghanaanand verma)
    @Test
    public void test003() {
        response.body("name", hasItem("Ghanaanand Verma"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (ramma banerjee, manik gill, mr ekasksh agarawal )
    @Test
    public void test004() {
        response.body("name", hasItems("Ramaa Banerjee", "Manik Gill", "Mr. Ekaksh Agarwal"));
    }

    //5. Verify the email of userid = 5914055 is equal “ekaksh_mr_agarwal@kuhlman-shields.test”
    @Test
    public void test005() {
        response.body("find{it.id == 5914055}.email", equalTo("ekaksh_mr_agarwal@kuhlman-shields.test"));
    }

    //6. Verify the status is “Active” of user name is “Dandapaani Agarwal”
    @Test
    public void test006() {
        response.body("find{it.status == 'active'}.name", equalTo("Tara Panicker"));
    }

    //7. Verify the Gender = male
    @Test
    public void test007() {
        response.body("find{it.name == 'Brahmdev Devar'}.gender", equalTo("male"));
    }


}
