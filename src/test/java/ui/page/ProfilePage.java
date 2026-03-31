package ui.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    // Локаторы - добавили static final
    private static final By PROFILE_INFO = By.xpath("//p[text()='В этом разделе вы можете изменить свои персональные данные']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверить отображение страницы профиля")
    public boolean isProfilePageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PROFILE_INFO));
        return driver.findElement(PROFILE_INFO).isDisplayed();
    }
}