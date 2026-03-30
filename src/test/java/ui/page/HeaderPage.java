package ui.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderPage extends BasePage {

    // Локаторы - добавили static final
    private static final By PERSONAL_ACCOUNT_BUTTON = By.xpath("//p[text()='Личный Кабинет']");
    private static final By CONSTRUCTOR_BUTTON = By.xpath("//p[text()='Конструктор']");
    private static final By LOGO = By.className("AppHeader_header__logo__2D0X2");

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public void clickPersonalAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(PERSONAL_ACCOUNT_BUTTON)).click();
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructor() {
        wait.until(ExpectedConditions.elementToBeClickable(CONSTRUCTOR_BUTTON)).click();
    }

    @Step("Клик по логотипу")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGO)).click();
    }
}