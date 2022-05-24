package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderTest {
    private SelenideElement form;
    private String validName = "Андрей Гончаров";
    private String validPhone = "+79154785421";

    @BeforeEach
    public void openResource() {
        open("http://localhost:9999");
        form = $("form");
    }

    // задание 1
    @Test
    public void shouldSendFormWithPositiveValues() {
        form.$("[data-test-id = name] input").setValue(validName);
        form.$("[data-test-id = phone] input").setValue(validPhone);
        form.$("[data-test-id = agreement]").click();
        form.$("button").click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        $("[data-test-id = order-success]").shouldHave(exactText(expected));
    }

    // задание 2
    @Test
    public void shouldNotSendEmptyForm() {
        form.$("button").click();

        $(".input_invalid").should(exist);
    }

    @Test
    public void shouldNotSendFormWithEmptyName() {
        form.$("[data-test-id = phone] input").setValue(validPhone);
        form.$("[data-test-id = agreement]").click();
        form.$("button").click();

        $(".input_invalid").should(exist);
    }

    @Test
    public void shouldNotSendFormWithEmptyPhone() {
        form.$("[data-test-id = name] input").setValue(validName);
        form.$("[data-test-id = agreement]").click();
        form.$("button").click();

        $(".input_invalid").should(exist);
    }

    @Test
    public void shouldNotSendFormWithEmptyAgreement() {
        form.$("[data-test-id = name] input").setValue(validName);
        form.$("[data-test-id = phone] input").setValue(validPhone);
        form.$("button").click();

        $(".input_invalid").should(exist);
    }

    @Test
    public void shouldNotSendFormWithInvalidArguements() {
        form.$("[data-test-id = name] input").setValue("Ivan Petrov");
        form.$("[data-test-id = phone] input").setValue("111");
        form.$("[data-test-id = agreement]").click();
        form.$("button").click();

        $(".input_invalid").should(exist);
    }

    @Test
    public void shouldShowNameRequirements() {
        form.$("[data-test-id = name] input").setValue("Ivan Petrov");
        form.$("[data-test-id = phone] input").setValue(validPhone);
        form.$("[data-test-id = agreement]").click();
        form.$("button").click();

        form.$("[data-test-id = name] .input__sub").shouldHave(text("Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldShowPhoneRequirements() {
        form.$("[data-test-id = name] input").setValue(validName);
        form.$("[data-test-id = phone] input").setValue("111");
        form.$("[data-test-id = agreement]").click();
        form.$("button").click();

        form.$("[data-test-id = phone] .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр"));
    }
}
