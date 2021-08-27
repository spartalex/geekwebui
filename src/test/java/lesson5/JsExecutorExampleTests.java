package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static lesson5.Helpers.clickWithJS;

public class JsExecutorExampleTests {
    WebDriver driver;
    WebDriverWait webDriverWait;
    private static final String BASE_URL = "https://ya.ru/";

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

        clickWithJS(driver, driver.findElement(By.xpath("//button")));
        javascriptExecutor.executeScript("window.alert('Привет!')");
        Thread.sleep(5000);
        Alert alert = driver.switchTo().alert();
        alert.accept();

        Thread.sleep(2000);

        ((JavascriptExecutor) driver).executeScript("window.open()");

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());

        Thread.sleep(3000);

        driver.switchTo().window(tabs.get(0));

        Thread.sleep(3000);

        driver.close();// одну вкладку заркыть

        Thread.sleep(5000);
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }
}
