package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderTest {
//    private WebDriver driver;
//    private ChromeOptions options;
//
//    @BeforeAll
//    static void setUp() {
//        //System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe"); // название драйвера и путь до драйвера
//        WebDriverManager.chromedriver().setup();
//    }
//
//    @BeforeEach
//    public void initializeDriver() {
//        options = new ChromeOptions();
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--headless");
//        driver = new ChromeDriver(options);
//    }
//
//    @AfterEach
//    public void close() {
//        if (driver != null) {
//            driver.quit();
//        }
//        driver = null;
//    }

//    @Test
//    public void shouldSendForm() {
//        driver.get("http://localhost:9999");
//
//        driver.findElement(By.cssSelector("[data-test-id = name] input")).sendKeys("Андрей Гончаров");
//        driver.findElement(By.cssSelector("[data-test-id = phone] input")).sendKeys("+79194885321");
//
//        driver.findElement(By.cssSelector("[data-test-id = agreement]")).click(); // по чекбоксу (согласие с политикой)
//        driver.findElement(By.tagName("button")).click(); // отправить
//
//        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
//        String actual = driver.findElement(By.cssSelector("[data-test-id = order-success]")).getText().trim();
//
//        assertEquals(expected, actual);
//    }

    @Test
    public void shouldSendForm() {
        open("http://localhost:9999");

        SelenideElement form = $("form");
        form.$("[data-test-id = name] input").setValue("Андрей Гончаров");
        form.$("[data-test-id = phone] input").setValue("+79154785421");
        form.$("[data-test-id = agreement]").click();
        form.$("button").click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        $("[data-test-id = order-success]").shouldHave(exactText(expected));

    }
}
