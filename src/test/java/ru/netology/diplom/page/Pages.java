package ru.netology.diplom.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.diplom.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Pages {
    private final ElementsCollection buttons = $$("button");
    private final ElementsCollection header = $$(".heading");
    private final ElementsCollection elements = $$(".input__control");
    private final SelenideElement cardNumberField = elements.get(0);
    private final SelenideElement cardMonthField = elements.get(1);
    private final SelenideElement cardYearField = elements.get(2);
    private final SelenideElement cardHolderField = elements.get(3);
    private final SelenideElement cardCVCField = elements.get(4);
    private final ElementsCollection subs = $$(".input__sub");
    private final SelenideElement successfulNotification = $(byText("Операция одобрена Банком."));
    private final SelenideElement errorNotification = $(byText("Ошибка! Банк отказал в проведении операции."));

    public void getCreditPage() {
        buttons.findBy(exactText("Купить в кредит")).click();
        header.get(2).shouldBe(visible).shouldHave(exactText("Кредит по данным карты"));
    }

    public void getPaymentPage() {
        buttons.findBy(exactText("Купить")).click();
        header.get(2).shouldBe(visible).shouldHave(exactText("Оплата по карте"));
    }
    public void sendCardData(DataHelper.CardData cardData) {
        cardNumberField.setValue(cardData.getCardNumber());
        cardMonthField.setValue(cardData.getCardMonth());
        cardYearField.setValue(cardData.getCardYear());
        cardHolderField.setValue(cardData.getCardHolder());
        cardCVCField.setValue(cardData.getCvc());
        buttons.findBy(exactText("Продолжить")).click();
    }
    public void getSuccessNotification() {
        successfulNotification.shouldBe(visible, Duration.ofSeconds(15));
    }
    public void getErrorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }
    public void getSubErrorEmptyForm() {
        subs.get(0).shouldBe(visible).shouldHave(exactText("Неверный формат"));
        subs.get(1).shouldBe(visible).shouldHave(exactText("Неверный формат"));
        subs.get(2).shouldBe(visible).shouldHave(exactText("Неверный формат"));
        subs.get(3).shouldBe(visible).shouldHave(exactText("Поле обязательно для заполнения"));
        subs.get(4).shouldBe(visible).shouldHave(exactText("Неверный формат"));
    }
    public void getSubError(String expectedText) {
        subs.findBy(exactText(expectedText)).shouldBe(visible);
    }

}
