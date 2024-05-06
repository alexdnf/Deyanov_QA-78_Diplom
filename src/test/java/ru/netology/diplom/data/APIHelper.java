package ru.netology.diplom.data;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIHelper {
    static String cardNumberValid = DataHelper.getValidCardNumber();
    static String cardNumberInvalid = DataHelper.getInvalidCardNumber();
    static String cardNumberRandom = DataHelper.getRandomCardNumber();
    static String year = DataHelper.getCardYear(0);
    static String month = DataHelper.getCardMonth(0);
    static String holder = DataHelper.getValidCardholderName();
    static String cvc = DataHelper.getValidCVCCode();

    public static void paymentAPIApprovedRequest() {
        given()
                .baseUri("http://localhost:8080/api/v1/")
                .body("{\n" +
                        "  \"number\": \"" + cardNumberValid + "\",\n" +
                        "  \"year\": \"" + year + "\",\n" +
                        "  \"month\": \""+ month +"\",\n" +
                        "  \"holder\": \""+ holder +"\",\n" +
                        "  \"cvc\": \"" + cvc + "\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/pay")

                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));
    }
    public static void paymentAPIDeclinedRequest() {
        given()
                .baseUri("http://localhost:8080/api/v1/")
                .body("{\n" +
                        "  \"number\": \""+ cardNumberInvalid +"\",\n" +
                        "  \"year\": \"" + year + "\",\n" +
                        "  \"month\": \"" + month + "\",\n" +
                        "  \"holder\": \"" + holder + "\",\n" +
                        "  \"cvc\": \"" + cvc + "\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/pay")

                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"));
    }
    public static void paymentAPI400Request() {
        given()
                .baseUri("http://localhost:8080/api/v1/")
                .body("{\n" +
                        "  \"number\": \"" + cardNumberRandom + "\",\n" +
                        "  \"year\": \"" + year + "\",\n" +
                        "  \"month\": \"" + month + "\",\n" +
                        "  \"holder\": \"" + holder + "\",\n" +
                        "  \"cvc\": \"" + cvc + "\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/pay")

                .then()
                .statusCode(400)
                .body("massage", equalTo("Invalid Value Provided"));
    }
    public static void creditAPIApprovedRequest() {
        given()
                .baseUri("http://localhost:8080/api/v1/")
                .body("{\n" +
                        "  \"number\": \"" + cardNumberValid + "\",\n" +
                        "  \"year\": \"" + year + "\",\n" +
                        "  \"month\": \"" + month + "\",\n" +
                        "  \"holder\": \"" + holder + "\",\n" +
                        "  \"cvc\": \"" + cvc + "\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/credit")

                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));
    }
    public static void creditAPIDeclinedRequest() {
        given()
                .baseUri("http://localhost:8080/api/v1/")
                .body("{\n" +
                        "  \"number\": \"" + cardNumberInvalid + "\",\n" +
                        "  \"year\": \"" + year + "\",\n" +
                        "  \"month\": \" "+ month + "\",\n" +
                        "  \"holder\": \"" + holder + "\",\n" +
                        "  \"cvc\": \"" + cvc + "\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/credit")

                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"));
    }
    public static void creditAPI400Request() {
        given()
                .baseUri("http://localhost:8080/api/v1/")
                .body("{\n" +
                        "  \"number\": \"" + cardNumberRandom + "\",\n" +
                        "  \"year\": \"" + year + "\",\n" +
                        "  \"month\": \"" + month + "\",\n" +
                        "  \"holder\": \"" + holder + "\",\n" +
                        "  \"cvc\": \"" + cvc + "\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/credit")

                .then()
                .statusCode(400)
                .body("massage", equalTo("Invalid Value Provided"));
    }
}
