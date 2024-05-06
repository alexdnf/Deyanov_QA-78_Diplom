package ru.netology.diplom.data;

import com.codeborne.selenide.ElementsCollection;
import com.github.javafaker.Faker;
import lombok.Value;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$$;

public class DataHelper {
    public static Faker faker = new Faker(new Locale("en"));
    @Value
    public static class CardData {
        String cardNumber;
        String cardMonth;
        String cardYear;
        String cardHolder;
        String cvc;
    }
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


    public static CardData getValidCardData() {
        return new CardData(getValidCardNumber(), getCardMonth(0), getCardYear(0), getValidCardholderName(), getValidCVCCode());
    }
    public static CardData getInvalidCardData () {
        return new CardData(getInvalidCardNumber(), getCardMonth(0), getCardYear(0), getValidCardholderName(), getValidCVCCode());
    }
    public static CardData getRandomCardData() {
        return new CardData(getRandomCardNumber(), getCardMonth(0), getCardYear(0), getValidCardholderName(), getValidCVCCode());
    }
    public static CardData get000CardData() {
        return new CardData(get0000CardNumber(), getCardMonth(0), getCardYear(0), getValidCardholderName(), getValidCVCCode());
    }
    public static CardData getEmptyFieldsCardData() {
        return new CardData(null, null,null,null,null);
    }
    public static CardData getExpiredYearCardData() {
        return new CardData(getValidCardNumber(), getCardMonth(0), getCardYear(-1), getValidCardholderName(), getValidCVCCode());
    }
    public static CardData getExpiredMonthCardData() {
        return new CardData(getValidCardNumber(), getCardMonth(-1), getCardYear(0), getValidCardholderName(), getValidCVCCode());
    }
    public static CardData getRusNameCardData() {
        return new CardData(getValidCardNumber(), getCardMonth(0), getCardYear(0), getInvalidCardHolderNameRus(), getValidCVCCode());
    }
    public static CardData getNumbersNameCardData() {
        return new CardData(getValidCardNumber(), getCardMonth(0), getCardYear(0), getInvalidCardHolderNameNumbers(), getValidCVCCode());
    }
    public static CardData getSymbolsNameCardData() {
        return new CardData(getValidCardNumber(), getCardMonth(0), getCardYear(0), getInvalidCardHolderNameSymbols(), getValidCVCCode());
    }
    public static CardData getDoubleNameSpaceCardData() {
        return new CardData(getValidCardNumber(), getCardMonth(0), getCardYear(0), getValidCardholderDoubleNameSpace(), getValidCVCCode());
    }
    public static CardData getDoubleSurnameCardData() {
        return new CardData(getValidCardNumber(), getCardMonth(0), getCardYear(0), getValidCardholderDoubleSurname(), getValidCVCCode());
    }
    public static CardData getEmptyCardNumberData() {
        return new CardData(null, getCardMonth(0), getCardYear(0), getValidCardholderName(), getValidCVCCode());
    }
    public static CardData getEmptyMonthCardData() {
        return new CardData(getValidCardNumber(), null, getCardYear(0), getValidCardholderName(), getValidCVCCode());
    }
    public static CardData getEmptyYearCardData() {
        return new CardData(getValidCardNumber(), getCardMonth(0), null, getValidCardholderName(), getValidCVCCode());
    }
    public static CardData getEmptyCardHolderNameCardData() {
        return new CardData(getValidCardNumber(), getCardMonth(0), getCardYear(0), null, getValidCVCCode());
    }
    public static CardData getEmptyCVCCardData() {
        return new CardData(getValidCardNumber(), getCardMonth(0), getCardYear(0), getValidCardholderName(), null);
    }
    public static CardData getNotFullNumberCardData() {
        return new CardData(getNotFullCardNumber(),getCardMonth(0),getCardYear(0), getValidCardholderName(), getValidCVCCode());
    }
    public static CardData getNotFullMonthCardData() {
        return new CardData(getValidCardNumber(),getNotFullMonth(),getCardYear(0), getValidCardholderName(), getValidCVCCode());
    }
    public static CardData getNotFullYearCardData() {
        return new CardData(getValidCardNumber(),getCardMonth(0),getNotFullCardYear(), getValidCardholderName(), getValidCVCCode());
    }
    public static CardData getNotFullCVCCardData() {
        return new CardData(getValidCardNumber(),getCardMonth(0),getCardYear(0), getValidCardholderName(), getNotFullCVCCode());
    }
}
