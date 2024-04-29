package ru.netology.diplom.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.diplom.data.DataHelper;
import ru.netology.diplom.data.SQLHelper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


class APITest {
    String cardNumberValid = DataHelper.getValidCardNumber();
    String cardNumberInvalid = DataHelper.getInvalidCardNumber();
    String cardNumberRandom = DataHelper.getRandomCardNumber();
    String year = DataHelper.getCardYear(0);
    String month = DataHelper.getCardMonth(0);
    String holder = DataHelper.getValidCardholderName();
    String cvc = DataHelper.getValidCVCCode();
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldReturnApprovedStatusPayment() throws InterruptedException {
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
        Thread.sleep(10000);
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusPayment());
        Assertions.assertEquals(4500000, SQLHelper.getAmountPayment());
    }

    @Test
    void shouldReturnDeclinedStatusPayment() throws InterruptedException {
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
        Thread.sleep(10000);
        Assertions.assertEquals("DECLINED", SQLHelper.getStatusPayment());
    }

    @Test
    void shouldReturn400StatusIfRandomCardPayment() {
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

    @Test
    void shouldReturnApprovedStatusCredit() throws InterruptedException {
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
        Thread.sleep(10000);
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusCredit());
    }

    @Test
    void shouldReturnDeclinedStatusCredit() throws InterruptedException {
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
        Thread.sleep(10000);
        Assertions.assertEquals("DECLINED", SQLHelper.getStatusCredit());
    }

    @Test
    void shouldReturn400StatusIfRandomCardCredit() {
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