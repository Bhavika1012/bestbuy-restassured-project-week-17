package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest extends TestBase {

    static ValidatableResponse response;
    static int storeId;
    static String name = "John" + TestUtils.getRandomValue();
    static String UpdatedName = "Smith" + TestUtils.getRandomValue();
    static String type = "Testing";
    static String address = "58" + TestUtils.getRandomValue() + "Road";

    static String address2 = TestUtils.getRandomValue() + "High Road";
    static String city = "London";

    static String state = "Middlesex";
    static String zip = "NW4 6YS";

    static List<String> services;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
    }

    @Test
    public void createStore() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);


        Response response = given()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post("/stores");
        response.then().log().all().statusCode(201);
        response.prettyPrint();

    }

    @Test
    public void getStoreDetails() {

        Response response = given()
                .when()
                .get("/stores/8921");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void updateStoreDetails(){
        StorePojo storePojo = new StorePojo();
        storePojo.setName("John");
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity("London");
        storePojo.setState(state);
        storePojo.setZip(zip);


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .put("/stores/8921");
        response.then().log().all().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteStoreDetails(){
        Response response = given()
                .pathParam("id",8921)
                .when()
                .delete("/stores/{id}");
        response.then().log().all().statusCode(200);

        Response response1 = given()
                .pathParam("id",8921)
                .when()
                .get("/stores/{id}");
        response1.then().statusCode(404);
        response1.prettyPrint();

    }
}
