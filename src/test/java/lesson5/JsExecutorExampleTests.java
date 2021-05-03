package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static lesson5.Helpers.clickWithJS;

public class JsExecutorExampleTests {
    WebDriver driver;
    WebDriverWait webDriverWait;
    private static final String BASE_URL = "https://afisha.ru/";

    @BeforeSuite
    void setupDataBase() {
        System.out.println("Before suite setup database");
    }

    @BeforeTest
    void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    void setUpBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
        driver.get(BASE_URL);
    }

    @Test
    void jsExecuteTest() throws InterruptedException {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        Thread.sleep(2000);
        javascriptExecutor.executeScript("document.getElementById('ad__id_5').remove()");
        Thread.sleep(2000);

        clickWithJS(driver, driver.findElement(By.xpath("//a[text()='Войти']")));
        javascriptExecutor.executeScript("window.alert('Привет!')");
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        alert.accept();

        Thread.sleep(2000);
    }
}
