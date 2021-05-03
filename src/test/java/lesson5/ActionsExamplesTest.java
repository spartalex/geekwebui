package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import lesson4.BoxTests;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static lesson5.BackGroundColor.hasColor;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class ActionsExamplesTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    private static final String BASE_URL = "https://crm.geekbrains.space/";

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
        login();
    }

    @Test(description = "Демонстация actions в crm", enabled = true)
    void dragAndDropTest() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//i[@class='icon-bar-chart']//ancestor::span[@class]")))
                .build()
                .perform();
        driver.findElement(By.xpath("//span[text()='Управение панелями инструментов']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Настройки представления']")));
        driver.findElement(By.xpath("//a[@title='Настройки представления']")).click();
        actions.clickAndHold(driver.findElement(By.xpath("//label[text()='Наименование']//ancestor::tr//span[@title='Move column']")))
                .dragAndDrop(driver.findElement(By.xpath("//label[text()='Наименование']//ancestor::tr")),
                        driver.findElement(By.xpath("//label[text()='Владелец']//ancestor::tr")))
                .build()
                .perform();
        //Thread.sleep(2000);//TODO:Заменить на нормальное ожидание
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))//Всего ждем 5 секунд
                .pollingEvery(Duration.ofMillis(50)); //Условие проверяем каждые 50 миллисекунд;

        //Само условие - получаем список заголовков и получим true, когда на 1 месте будет ВЛАДЕЛЕЦ
        wait.until(driver -> driver.findElements(
                By.xpath("//thead[@class='grid-header']//span[@class='grid-header-cell__label']")).get(0).getText()
                .equals("ВЛАДЕЛЕЦ"));

        List<WebElement> headers = driver.findElements(
                By.xpath("//thead[@class='grid-header']//span[@class='grid-header-cell__label']"));
        assertThat(headers.get(0).getText(), containsString("ВЛАДЕЛЕЦ"));
        assertThat(headers.get(0), hasText("ВЛАДЕЛЕЦ"));
        //assertThat(headers.get(0), not(isDisplayed()));
        Assert.assertTrue(headers.get(0).isDisplayed());
        Assert.assertEquals(headers.get(0).getText(), "ВЛАДЕЛЕЦ");

        String backGroundColorBefore = driver.findElement(By.xpath("//tbody[@class='grid-body']//tr[1]"))
                .getCssValue("background-color");

        driver.findElement(By.xpath("//thead[@class='grid-header']//input[@type='checkbox']")).click();
        WebElement gridRow = driver.findElement(By.xpath("//tbody[@class='grid-body']//tr[1]"));
        String backgroundColor = gridRow.getCssValue("background-color");
        assertThat(gridRow, hasColor("rgba(254, 250, 237, 1)"));
    }


    private void login() {
        driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();
    }

    @DataProvider
    public static Object[][] stringData() {
        return new Object[][]{
                {"test1", new BoxTests(), "adssdaf"},
                {"test3", new BoxTests()}
        };
    }

    @Test(dataProvider = "stringData")
    void dataProviderTest(String str, BoxTests tests, String test2) {

    }

    @AfterMethod
    void closeBrowser() {
        driver.quit();
    }

}
