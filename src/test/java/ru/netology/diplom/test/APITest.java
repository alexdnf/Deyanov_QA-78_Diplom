package ru.netology.diplom.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.diplom.data.APIHelper;
import ru.netology.diplom.data.SQLHelper;


class APITest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldReturnApprovedStatusPayment() {
        APIHelper.paymentAPIApprovedRequest();
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusPayment());
        Assertions.assertEquals(4500000, SQLHelper.getAmountPayment());
    }

    @Test
    void shouldReturnDeclinedStatusPayment()  {
        APIHelper.paymentAPIDeclinedRequest();
        Assertions.assertEquals("DECLINED", SQLHelper.getStatusPayment());
    }

    @Test
    void shouldReturn400StatusIfRandomCardPayment() {
        APIHelper.paymentAPI400Request();
    }

    @Test
    void shouldReturnApprovedStatusCredit() {
        APIHelper.creditAPIApprovedRequest();
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusCredit());
    }

    @Test
    void shouldReturnDeclinedStatusCredit()  {
        APIHelper.creditAPIDeclinedRequest();
        Assertions.assertEquals("DECLINED", SQLHelper.getStatusCredit());
    }

    @Test
    void shouldReturn400StatusIfRandomCardCredit() {
        APIHelper.creditAPI400Request();
    }

}