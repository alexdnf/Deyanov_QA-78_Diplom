package ru.netology.diplom.data;

import com.codeborne.selenide.ElementsCollection;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class DataHelper {
    public static Faker faker = new Faker(new Locale("en"));
    public static ElementsCollection elements = $$(".input__control");


    public static String getValidCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getInvalidCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getRandomCardNumber() {
        return faker.business().creditCardNumber();
    }

    public static String getValidCardholderName() {
        return faker.name().fullName();
    }

    public static String getValidCardholderDoubleNameSpace() {
        return "Ivan Ivanov Petrov";
    }

    public static String getValidCardholderDoubleNameDash() {
        return "Ivan Ivanov-Petrov";
    }

    public static String getInvalidCardHolderNameRus() {
        return "Иван Иванов";
    }

    public static String getInvalidCardHolderNameNumbers() {
        return "12345 12345";
    }

    public static String getInvalidCardHolderNameSymbols() {
        return "Ivan Ivan;v";
    }

    public static String getValidCVCCode() {
        return faker.numerify("###");
    }

    public static String getCardMonth(int count) {
        return LocalDate.now().plusMonths(count).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getCardYear(int count) {
        return LocalDate.now().plusYears(count).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static void getValidCardData() {

        var validMonth = getCardMonth(0);
        var validYear = getCardYear(0);

        elements.get(0).setValue(getValidCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getValidCardholderName());
        elements.get(4).setValue(getValidCVCCode());

    }

    public static void getInvalidCardData() {

        var validMonth = getCardMonth(0);
        var validYear = getCardYear(0);

        elements.get(0).setValue(getInvalidCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getValidCardholderName());
        elements.get(4).setValue(getValidCVCCode());

    }

    public static void getRandomCardData() {
        var validMonth = getCardMonth(0);
        var validYear = getCardYear(0);

        elements.get(0).setValue(getRandomCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getValidCardholderName());
        elements.get(4).setValue(getValidCVCCode());
    }

    public static void getExpiredYearCardData() {
        var validMonth = getCardMonth(0);
        var expiredYear = getCardYear(-1);

        elements.get(0).setValue(getValidCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(expiredYear);
        elements.get(3).setValue(getValidCardholderName());
        elements.get(4).setValue(getValidCVCCode());
    }
    public static void getExpiredMonthCardData() {
        var expiredMonth = getCardMonth(-1);
        var validYear = getCardYear(0);

        elements.get(0).setValue(getValidCardNumber());
        elements.get(1).setValue(expiredMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getValidCardholderName());
        elements.get(4).setValue(getValidCVCCode());
    }

    public static void getRusNameCardData() {
        var validMonth = getCardMonth(0);
        var validYear = getCardYear(0);

        elements.get(0).setValue(getValidCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getInvalidCardHolderNameRus());
        elements.get(4).setValue(getValidCVCCode());
    }

    public static void getSymbolsNameCardData() {
        var validMonth = getCardMonth(0);
        var validYear = getCardYear(0);

        elements.get(0).setValue(getValidCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getInvalidCardHolderNameSymbols());
        elements.get(4).setValue(getValidCVCCode());
    }

    public static void getNumbersNameCardData() {
        var validMonth = getCardMonth(0);
        var validYear = getCardYear(0);

        elements.get(0).setValue(getValidCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getInvalidCardHolderNameNumbers());
        elements.get(4).setValue(getValidCVCCode());
    }

    public static void getDoubleNameSpaceCardData() {
        var validMonth = getCardMonth(0);
        var validYear = getCardYear(0);

        elements.get(0).setValue(getValidCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getValidCardholderDoubleNameSpace());
        elements.get(4).setValue(getValidCVCCode());
    }

    public static void getDoubleNameDashCardData() {
        var validMonth = getCardMonth(0);
        var validYear = getCardYear(0);

        elements.get(0).setValue(getValidCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getValidCardholderDoubleNameDash());
        elements.get(4).setValue(getValidCVCCode());
    }
}