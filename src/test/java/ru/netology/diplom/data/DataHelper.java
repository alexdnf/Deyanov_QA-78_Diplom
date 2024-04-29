package ru.netology.diplom.data;

import com.codeborne.selenide.ElementsCollection;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
    public static String get0000CardNumber() { return "0000 0000 0000 0000"; }
    public static String getNotFullCardNumber() {
        return faker.numerify("#### #### #### ###");
    }

    public static String getValidCardholderName() {
        String name = faker.name().firstName();
        String surname = faker.name().lastName();
        return name + " " + surname;
    }

    public static String getValidCardholderDoubleNameSpace() {
        return "Ivan Sergey Petrov";
    }

    public static String getValidCardholderDoubleSurname() {
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
    public static String getNotFullCVCCode() {
        return faker.numerify("##");
    }
    public static String getCardMonth(int count) {
        return LocalDate.now().plusMonths(count).format(DateTimeFormatter.ofPattern("MM"));
    }
    public static String getNotFullMonth() {
        return faker.numerify("#");
    }

    public static String getCardYear(int count) {
        return LocalDate.now().plusYears(count).format(DateTimeFormatter.ofPattern("yy"));
    }
    public static String getNotFullCardYear() {
        return faker.numerify("#");
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

    public static void getDoubleSurnameCardData() {
        var validMonth = getCardMonth(0);
        var validYear = getCardYear(0);

        elements.get(0).setValue(getValidCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getValidCardholderDoubleSurname());
        elements.get(4).setValue(getValidCVCCode());
    }

    public static void get0000CardData() {
        var validMonth = getCardMonth(0);
        var validYear = getCardYear(0);

        elements.get(0).setValue(get0000CardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getValidCardholderName());
        elements.get(4).setValue(getValidCVCCode());
    }
    public static void getNotFullCardNumberData() {
        var validMonth = getCardMonth(0);
        var validYear = getCardYear(0);

        elements.get(0).setValue(getNotFullCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getValidCardholderName());
        elements.get(4).setValue(getValidCVCCode());
    }
    public static void getNotFullMonthCardData() {
        var month = getNotFullMonth();
        var validYear = getCardYear(0);

        elements.get(0).setValue(getValidCardNumber());
        elements.get(1).setValue(month);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getValidCardholderName());
        elements.get(4).setValue(getValidCVCCode());
    }
    public static void getNotFullYearCardData() {
        var validMonth = getCardMonth(0);
        var year = getNotFullCardYear();

        elements.get(0).setValue(getValidCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(year);
        elements.get(3).setValue(getValidCardholderName());
        elements.get(4).setValue(getValidCVCCode());
    }
    public static void getNotFullCVCCardData() {
        var validMonth = getCardMonth(0);
        var validYear = getCardYear(0);

        elements.get(0).setValue(getValidCardNumber());
        elements.get(1).setValue(validMonth);
        elements.get(2).setValue(validYear);
        elements.get(3).setValue(getValidCardholderName());
        elements.get(4).setValue(getNotFullCVCCode());
    }
}