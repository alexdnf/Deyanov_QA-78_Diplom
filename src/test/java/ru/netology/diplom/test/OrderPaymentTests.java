package ru.netology.diplom.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.diplom.data.DataHelper;
import ru.netology.diplom.data.SQLHelper;
import ru.netology.diplom.page.Pages;

import static com.codeborne.selenide.Selenide.open;

public class OrderPaymentTests {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @Test
    void shouldSendFormValidCardPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getValidCardData());
        page.getSuccessNotification();

        Assertions.assertEquals("APPROVED", SQLHelper.getStatusPayment());
        Assertions.assertEquals(4500000, SQLHelper.getAmountPayment());
    }

    @Test
    void shouldSendFormWithErrorInvalidCardPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getInvalidCardData());
        page.getErrorNotification();

        Assertions.assertEquals("DECLINED", SQLHelper.getStatusPayment());
    }
    @Test
    void shouldNotSendFormWith0000CardPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.get000CardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldSendFormWithErrorRandomCardPayment() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getRandomCardData());
        page.getErrorNotification();
    }
    @Test
    void shouldNotSendEmptyFormPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getEmptyFieldsCardData());
        page.getSubErrorEmptyForm();
    }
    @Test
    void shouldNotSendFormWithExpiredYearCardPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getExpiredYearCardData());
        page.getSubError("Истёк срок действия карты");
    }
    @Test
    void shouldNotSendFormWithExpiredMonthCardPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getExpiredMonthCardData());
        page.getSubError("Неверно указан срок действия карты");
    }
    @Test
    void shouldNotSendFormWithInvalidNameRusPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getRusNameCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithInvalidNameSymbolsPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getSymbolsNameCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithInvalidNameNumbersPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getNumbersNameCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldSendFormWithDoubleNameSpacePayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getDoubleNameSpaceCardData());
        page.getSuccessNotification();
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusPayment());
        Assertions.assertEquals(4500000, SQLHelper.getAmountPayment());
    }
    @Test
    void shouldSendFormWithDoubleSurnamePayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getDoubleSurnameCardData());
        page.getSuccessNotification();

        Assertions.assertEquals("APPROVED", SQLHelper.getStatusPayment());
        Assertions.assertEquals(4500000, SQLHelper.getAmountPayment());
    }
    @Test
    void shouldNotSendFormWithEmptyCardNumberPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getEmptyCardNumberData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithEmptyMonthPayment() {
       Pages page = new Pages();

       page.getPaymentPage();
       page.sendCardData(DataHelper.getEmptyMonthCardData());
       page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithEmptyYearPayment() {
       Pages page = new Pages();

       page.getPaymentPage();
       page.sendCardData(DataHelper.getEmptyYearCardData());
       page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithEmptyCardHolderPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getEmptyCardHolderNameCardData());
        page.getSubError("Поле обязательно для заполнения");
    }
    @Test
    void shouldNotSendFormWithEmptyCVCPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getEmptyCVCCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithNotFullCardNumberPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getNotFullNumberCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithNotFullMonthCardPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getNotFullMonthCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithNotFullYearCardPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getNotFullYearCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithNotFullCVCCardPayment() {
        Pages page = new Pages();

        page.getPaymentPage();
        page.sendCardData(DataHelper.getNotFullCVCCardData());
        page.getSubError("Неверный формат");
    }
}
