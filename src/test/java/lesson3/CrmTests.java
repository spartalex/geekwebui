package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CrmTests {
    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";

    //создать заявку на расход
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        login();

        driver.get("https://crm.geekbrains.space/expense-request/");

        driver.findElement(By.xpath("//a[text()='Создать заявку на расход']")).click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.urlContains("create"));
        //webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("crm_expense_request[description]")));

        driver.findElement(By.name("crm_expense_request[description]")).sendKeys("test");

        Select businessUnit = new Select(driver.findElement(By.name("crm_expense_request[businessUnit]")));
        businessUnit.selectByVisibleText("Research & Development");

        Select expenceSelect = new Select(driver.findElement(By.name("crm_expense_request[expenditure]")));
        expenceSelect.selectByVisibleText("01101 - ОС: вычислительная техника инфраструктуры");

        driver.findElement(By.name("crm_expense_request[sumPlan]")).sendKeys("123");

        //Укаажите дату
        driver.findElement(By.xpath("//label[@class='required']//ancestor::div[contains(@class,'control')]" +
                "//input[@placeholder='Укажите дату']")).click();
        //datepicker-input  hasDatepicker error
        driver.findElement(By.xpath("//a[text()='27']")).click();

        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")).click();

        //так плохо-
        //webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Заявка на расход сохранена']"))));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Заявка на расход сохранена']")));

        driver.findElement(By.xpath("//*[text()='Заявка на расход сохранена']"));

        Thread.sleep(5000);

        driver.quit();

    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);
        driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();
    }

}
