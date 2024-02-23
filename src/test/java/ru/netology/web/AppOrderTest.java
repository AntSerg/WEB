package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AppOrderTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown () {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldBeSuccessForm () {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Пушкин Александр");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79217990606");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        WebElement actualTextElement = driver.findElement(By.cssSelector("[data-test-id='order-success"));
        String actualText = actualTextElement.getText().trim();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",
                actualText);
        Assertions.assertTrue(actualTextElement.isDisplayed());
    }
}
