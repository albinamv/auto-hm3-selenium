package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    private WebDriver driver;
    private ChromeOptions options;

    @BeforeAll
    static void setUp() {
        //System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe"); // название драйвера и путь до драйвера
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void initializeDriver() {
        options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }

    @Test
    public void shouldSendForm() {
        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id = name] input")).sendKeys("Андрей Гончаров");
        driver.findElement(By.cssSelector("[data-test-id = phone] input")).sendKeys("+79194885321");

        driver.findElement(By.cssSelector("[data-test-id = agreement]")).click(); // по чекбоксу (согласие с политикой)
        driver.findElement(By.tagName("button")).click(); // отправить

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id = order-success]")).getText().trim();

        assertEquals(expected, actual);
    }
}
