package ui.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    private static final String URL = "https://stellarburgers.education-services.ru/register";

    // Локаторы - добавили static final
    private static final By NAME_INPUT = By.xpath("//label[text()='Имя']/following-sibling::input");
    private static final By EMAIL_INPUT = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_INPUT = By.xpath("//input[@type='password']");
    private static final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By LOGIN_LINK = By.xpath("//a[text()='Войти']");
    private static final By ERROR_MESSAGE = By.xpath("//p[text()='Некорректный пароль']");
    private static final By LOGIN_FORM_BUTTON = By.xpath("//button[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу регистрации")
    public void open() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(REGISTER_BUTTON));
    }

    @Step("Ввести имя: {name}")
    public void setName(String name) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(NAME_INPUT));
        element.sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(EMAIL_INPUT));
        element.sendKeys(email);
    }

    @Step("Ввести пароль: {password}")
    public void setPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(PASSWORD_INPUT));
        element.sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(REGISTER_BUTTON));
        button.click();
    }

    @Step("Выполнить регистрацию")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_LINK)).click();
    }

    @Step("Проверить отображение ошибки пароля")
    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ожидать успешную регистрацию (редирект на страницу логина)")
    public boolean waitForSuccessfulRegistration() {
        try {
            boolean urlContainsLogin = wait.until(
                    ExpectedConditions.urlContains("login")
            );
            boolean loginButtonVisible = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(LOGIN_FORM_BUTTON)
            ).isDisplayed();
            return urlContainsLogin || loginButtonVisible;
        } catch (Exception e) {
            return false;
        }
    }
}