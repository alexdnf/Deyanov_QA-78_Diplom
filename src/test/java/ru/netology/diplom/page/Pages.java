package ru.netology.diplom.page;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class Pages {
    public static ElementsCollection buttons = $$("button");

    public static void getCreditPage() {
        buttons.findBy(exactText("Купить в кредит")).click();
        $$(".heading").get(2).shouldBe(visible).shouldHave(exactText("Кредит по данным карты"));
    }

    public static void getPaymentPage() {
        buttons.findBy(exactText("Купить")).click();
        $$(".heading").get(2).shouldBe(visible).shouldHave(exactText("Оплата по карте"));
    }
}
