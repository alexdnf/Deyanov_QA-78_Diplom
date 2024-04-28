package ru.netology.diplom.test;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


class APITest {
    @Test
    void shouldReturnCardForm() {
        given()
                .baseUri("http://localhost:8080/")

                .when()
                .get()

                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturnApprovedStatusPayment() {
        given()
                .baseUri("http://localhost:8080/api/v1/")
                .body("{\n" +
                        "  \"number\": \"4444 4444 4444 4441\",\n" +
                        "  \"year\": \"24\",\n" +
                        "  \"month\": \"05\",\n" +
                        "  \"holder\": \"Ivan Ivanov\",\n" +
                        "  \"cvc\": \"111\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/pay")

                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));
    }

    @Test
    void shouldReturnDeclinedStatusPayment() {
        given()
                .baseUri("http://localhost:8080/api/v1/")
                .body("{\n" +
                        "  \"number\": \"4444 4444 4444 4442\",\n" +
                        "  \"year\": \"24\",\n" +
                        "  \"month\": \"05\",\n" +
                        "  \"holder\": \"Ivan Ivanov\",\n" +
                        "  \"cvc\": \"111\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/pay")

                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"));
    }

    @Test
    void shouldReturn500StatusIfRandomCardPayment() {
        given()
                .baseUri("http://localhost:8080/api/v1/")
                .body("{\n" +
                        "  \"number\": \"0000 0000 0000 0000\",\n" +
                        "  \"year\": \"24\",\n" +
                        "  \"month\": \"05\",\n" +
                        "  \"holder\": \"Ivan Ivanov\",\n" +
                        "  \"cvc\": \"111\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/pay")

                .then()
                .statusCode(500)
                .body("error", equalTo("Internal Server Error"));
    }

    @Test
    void shouldReturnApprovedStatusCredit() {
        given()
                .baseUri("http://localhost:8080/api/v1/")
                .body("{\n" +
                        "  \"number\": \"4444 4444 4444 4441\",\n" +
                        "  \"year\": \"24\",\n" +
                        "  \"month\": \"05\",\n" +
                        "  \"holder\": \"Ivan Ivanov\",\n" +
                        "  \"cvc\": \"111\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/credit")

                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));
    }

    @Test
    void shouldReturnDeclinedStatusCredit() {
        given()
                .baseUri("http://localhost:8080/api/v1/")
                .body("{\n" +
                        "  \"number\": \"4444 4444 4444 4442\",\n" +
                        "  \"year\": \"24\",\n" +
                        "  \"month\": \"05\",\n" +
                        "  \"holder\": \"Ivan Ivanov\",\n" +
                        "  \"cvc\": \"111\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/credit")

                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"));
    }

    @Test
    void shouldReturn500StatusIfRandomCardCredit() {
        given()
                .baseUri("http://localhost:8080/api/v1/")
                .body("{\n" +
                        "  \"number\": \"0000 0000 0000 0000\",\n" +
                        "  \"year\": \"24\",\n" +
                        "  \"month\": \"05\",\n" +
                        "  \"holder\": \"Ivan Ivanov\",\n" +
                        "  \"cvc\": \"111\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/credit")

                .then()
                .statusCode(500)
                .body("error", equalTo("Internal Server Error"));
    }

}

