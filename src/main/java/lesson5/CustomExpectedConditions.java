package lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomExpectedConditions {
    public static ExpectedCondition<Boolean> elementFirstInCollection(final By locator, final String text) {
        return driver -> driver.findElements(
                By.xpath("//thead[@class='grid-header']//span[@class='grid-header-cell__label']")).get(0).getText()
                .equals(text);
    }
}
