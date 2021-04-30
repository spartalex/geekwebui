package lesson3;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginToDiary {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://diary.ru");
        Cookie sessionCookie = driver.manage().getCookieNamed("PHPSESSID");
        driver.manage().deleteCookie(sessionCookie);
        Cookie cookie = new Cookie("PHPSESSID", "hiedlbq6q6dav3d2ih2e0bd4ft");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
        Thread.sleep(50000);
        driver.close();
    }
}
