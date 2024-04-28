package ru.netology.diplom.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.diplom.data.DataHelper;
import ru.netology.diplom.data.SQLHelper;
import ru.netology.diplom.page.Pages;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class OrderTest {
    public ElementsCollection buttons = $$("button");
    public static ElementsCollection elements = $$(".input__control");
    public static ElementsCollection subs = $$(".input__sub");
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
    void shouldSendFormValidCardPayment() throws InterruptedException {
        Pages.getPaymentPage();
        DataHelper.getValidCardData();
        buttons.findBy(exactText("Продолжить")).click();
        $(byText("Операция одобрена Банком.")).shouldBe(visible, Duration.ofSeconds(15));
        Thread.sleep(10000);
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusPayment());
        Assertions.assertEquals("45000", SQLHelper.getAmountPayment());
    }

    @Test
    void shouldSendFormValidCardCredit() throws InterruptedException {
        Pages.getCreditPage();
        DataHelper.getValidCardData();
        buttons.findBy(exactText("Продолжить")).click();
        $(byText("Операция одобрена Банком.")).shouldBe(visible, Duration.ofSeconds(15));
        Thread.sleep(10000);
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusCredit());
    }

    @Test
    void shouldSendFormWithErrorInvalidCardPayment() {
        Pages.getPaymentPage();
        DataHelper.getInvalidCardData();
        buttons.findBy(exactText("Продолжить")).click();
        $(byText("Ошибка! Банк отказал в проведении операции.")).shouldBe(visible, Duration.ofSeconds(15));
        Assertions.assertEquals("DECLINED", SQLHelper.getStatusPayment());
    }

    @Test
    void shouldSendFormWithErrorInvalidCardCredit() {
        Pages.getCreditPage();
        DataHelper.getInvalidCardData();
        buttons.findBy(exactText("Продолжить")).click();
        $(byText("Ошибка! Банк отказал в проведении операции.")).shouldBe(visible, Duration.ofSeconds(15));
        Assertions.assertEquals("DECLINED", SQLHelper.getStatusCredit());
    }

    @Test
    void shouldSendFormWithErrorRandomCardCredit() {
        Pages.getCreditPage();
        DataHelper.getRandomCardData();
        buttons.findBy(exactText("Продолжить")).click();
        $(byText("Ошибка! Банк отказал в проведении операции.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSendFormWithErrorRandomCardPayment() {
        Pages.getPaymentPage();
        DataHelper.getRandomCardData();
        buttons.findBy(exactText("Продолжить")).click();
        $(byText("Ошибка! Банк отказал в проведении операции.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotSendEmptyFormPayment() {

        Pages.getPaymentPage();
        buttons.findBy(exactText("Продолжить")).click();
        subs.get(0).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
        subs.get(1).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
        subs.get(2).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
        subs.get(3).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Поле обязательно для заполнения"));
        subs.get(4).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendEmptyFormCredit() {

        Pages.getCreditPage();
        buttons.findBy(exactText("Продолжить")).click();
        subs.get(0).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
        subs.get(1).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
        subs.get(2).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
        subs.get(3).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Поле обязательно для заполнения"));
        subs.get(4).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendFormWithExpiryCardPayment() {

        Pages.getPaymentPage();
        DataHelper.getExpiredCardData();
        buttons.findBy(exactText("Продолжить")).click();
        subs.get(0).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Истёк срок действия карты"));
    }

    @Test
    void shouldNotSendFormWithExpiryCardCredit() {

        Pages.getCreditPage();
        DataHelper.getExpiredCardData();
        buttons.findBy(exactText("Продолжить")).click();
        subs.get(0).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Истёк срок действия карты"));
    }

    @Test
    void shouldNotSendFormWithInvalidNameRusPayment() {

        Pages.getPaymentPage();
        DataHelper.getRusNameCardData();
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendFormWithInvalidNameRusCredit() {

        Pages.getCreditPage();
        DataHelper.getRusNameCardData();
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendFormWithInvalidNameSymbolsPayment() {

        Pages.getPaymentPage();
        DataHelper.getSymbolsNameCardData();
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendFormWithInvalidNameSymbolsCredit() {

        Pages.getCreditPage();
        DataHelper.getSymbolsNameCardData();
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldSendFormWithInvalidNameNumbersPayment() {

        Pages.getPaymentPage();
        DataHelper.getNumbersNameCardData();
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendFormWithInvalidNameNumbersCredit() {

        Pages.getCreditPage();
        DataHelper.getNumbersNameCardData();
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldSendFormWithDoubleNameSpacePayment() {
        Pages.getPaymentPage();
        DataHelper.getDoubleNameSpaceCardData();
        buttons.findBy(exactText("Продолжить")).click();
        $(byText("Операция одобрена Банком.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSendFormWithDoubleNameSpaceCredit() {
        Pages.getCreditPage();
        DataHelper.getDoubleNameSpaceCardData();
        buttons.findBy(exactText("Продолжить")).click();
        $(byText("Операция одобрена Банком.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSendFormWithDoubleNameDashPayment() {
        Pages.getPaymentPage();
        DataHelper.getDoubleNameDashCardData();
        buttons.findBy(exactText("Продолжить")).click();
        $(byText("Операция одобрена Банком.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSendFormWithDoubleNameDashCredit() {
        Pages.getCreditPage();
        DataHelper.getDoubleNameDashCardData();
        buttons.findBy(exactText("Продолжить")).click();
        $(byText("Операция одобрена Банком.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotSendFormWithEmptyCardNumberPayment() {
        Pages.getPaymentPage();
        DataHelper.getValidCardData();
        elements.get(0).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendFormWithEmptyMonthPayment() {
        Pages.getPaymentPage();
        DataHelper.getValidCardData();
        elements.get(1).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendFormWithEmptyYearPayment() {
        Pages.getPaymentPage();
        DataHelper.getValidCardData();
        elements.get(2).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendFormWithEmptyCardHolderPayment() {
        Pages.getPaymentPage();
        DataHelper.getValidCardData();
        elements.get(3).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendFormWithEmptyCVCPayment() {
        Pages.getPaymentPage();
        DataHelper.getValidCardData();
        elements.get(4).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendFormWithEmptyCardNumberCredit() {
        Pages.getCreditPage();
        DataHelper.getValidCardData();
        elements.get(0).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendFormWithEmptyMonthCredit() {
        Pages.getCreditPage();
        DataHelper.getValidCardData();
        elements.get(1).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendFormWithEmptyYearCredit() {
        Pages.getCreditPage();
        DataHelper.getValidCardData();
        elements.get(2).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Test
    void shouldNotSendFormWithEmptyCardHolderCredit() {
        Pages.getCreditPage();
        DataHelper.getValidCardData();
        elements.get(3).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendFormWithEmptyCVCCredit() {
        Pages.getCreditPage();
        DataHelper.getValidCardData();
        elements.get(4).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        buttons.findBy(exactText("Продолжить")).click();
        subs.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

}
