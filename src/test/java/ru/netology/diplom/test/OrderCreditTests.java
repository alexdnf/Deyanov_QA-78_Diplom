package ru.netology.diplom.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.diplom.data.DataHelper;
import ru.netology.diplom.data.SQLHelper;
import ru.netology.diplom.page.Pages;

import static com.codeborne.selenide.Selenide.open;

public class OrderCreditTests {
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
    void shouldSendFormValidCardCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getValidCardData());
        page.getSuccessNotification();

        Assertions.assertEquals("APPROVED", SQLHelper.getStatusCredit());
    }
    @Test
    void shouldSendFormWithErrorInvalidCardCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getInvalidCardData());
        page.getErrorNotification();

        Assertions.assertEquals("DECLINED", SQLHelper.getStatusCredit());
    }
    @Test
    void shouldNotSendFormWith0000CardCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.get000CardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldSendFormWithErrorRandomCardCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getRandomCardData());
        page.getErrorNotification();
    }
    @Test
    void shouldNotSendEmptyFormCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getEmptyFieldsCardData());
        page.getSubErrorEmptyForm();
    }
    @Test
    void shouldNotSendFormWithExpiredYearCardCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getExpiredYearCardData());
        page.getSubError("Истёк срок действия карты");
    }
    @Test
    void shouldNotSendFormWithExpiredMonthCardCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getExpiredMonthCardData());
        page.getSubError("Неверно указан срок действия карты");
    }
    @Test
    void shouldNotSendFormWithInvalidNameRusCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getRusNameCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithInvalidNameSymbolsCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getSymbolsNameCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithInvalidNameNumbersCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getNumbersNameCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldSendFormWithDoubleNameSpaceCredit()  {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getDoubleNameSpaceCardData());
        page.getSuccessNotification();
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusCredit());

    }
    @Test
    void shouldSendFormWithDoubleSurnameCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getDoubleSurnameCardData());
        page.getSuccessNotification();
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusCredit());
    }
    @Test
    void shouldNotSendFormWithEmptyCardNumberCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getEmptyCardNumberData());
        page.getSubError("Неверный формат");
    }

    @Test
    void shouldNotSendFormWithEmptyMonthCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getEmptyMonthCardData());
        page.getSubError("Неверный формат");
    }

    @Test
    void shouldNotSendFormWithEmptyYearCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getEmptyYearCardData());
        page.getSubError("Неверный формат");
    }

    @Test
    void shouldNotSendFormWithEmptyCardHolderCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getEmptyCardHolderNameCardData());
        page.getSubError("Поле обязательно для заполнения");
    }

    @Test
    void shouldNotSendFormWithEmptyCVCCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getEmptyCVCCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithNotFullCardNumberCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getNotFullNumberCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithNotFullMonthCardCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getNotFullMonthCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithNotFullYearCardCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getNotFullYearCardData());
        page.getSubError("Неверный формат");
    }
    @Test
    void shouldNotSendFormWithNotFullCVCCardCredit() {
        Pages page = new Pages();

        page.getCreditPage();
        page.sendCardData(DataHelper.getNotFullCVCCardData());
        page.getSubError("Неверный формат");
    }
}
