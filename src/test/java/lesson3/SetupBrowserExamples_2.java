package lesson3;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class SetupBrowserExamples_2 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications")
                .addArguments("--disable-popup-blocking")
                .addArguments("--no-sandbox")
                .addArguments("user-agent=Googlebot/2.1 (+http://www.google.com/bot.html)");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://google.com");

        driver.manage().window().maximize();
        Thread.sleep(5000);
        driver.manage().window().setSize(new Dimension(500, 500));
        Thread.sleep(5000);


        ((JavascriptExecutor) driver).executeScript("window.open()");

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());

        Thread.sleep(3000);

        driver.switchTo().window(tabs.get(0));

        Thread.sleep(3000);

        driver.close();// одну вкладку заркыть
        driver.quit();
    }
}
