package lesson5;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

public class BackGroundColor extends TypeSafeMatcher<WebElement> {
    private String colorToCompare;

    @Override
    protected boolean matchesSafely(WebElement item) {
        if (item.getCssValue("background-color").equals(colorToCompare)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void describeMismatchSafely(WebElement element, Description description) {
        description.appendText("color of element was: " + element.getCssValue("background-color"));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Background color expected " + colorToCompare);
    }

    public BackGroundColor(String colorToCompare) {
        this.colorToCompare = colorToCompare;
    }

    public static Matcher<WebElement> hasColor(String colorToCompare) {
        return new BackGroundColor(colorToCompare);
    }
}
