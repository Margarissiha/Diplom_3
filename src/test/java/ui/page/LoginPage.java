package ui.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private static final String URL = "https://stellarburgers.education-services.ru/login";

    // Локаторы
    private static final By EMAIL_INPUT = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_INPUT = By.xpath("//input[@type='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Войти']");
    private static final By REGISTER_LINK = By.xpath("//a[text()='Зарегистрироваться']");
    private static final By FORGOT_PASSWORD_LINK = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу логина")
    public void open() {
        driver.get(URL);
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT)).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT)).sendKeys(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON)).click();
    }

    @Step("Выполнить вход")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(REGISTER_LINK)).click();
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(FORGOT_PASSWORD_LINK)).click();
    }

    @Step("Ожидать загрузку страницы логина")
    public boolean waitForPageLoad() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}