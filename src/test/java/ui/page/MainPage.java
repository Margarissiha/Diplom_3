package ui.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    private static final String URL = "https://stellarburgers.education-services.ru/";

    // Локаторы - добавили static final
    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By PLACE_ORDER_BUTTON = By.xpath("//button[text()='Оформить заказ']");

    // Локаторы для табов конструктора
    private static final By BUNS_TAB = By.xpath("//span[text()='Булки']/parent::div");
    private static final By SAUCES_TAB = By.xpath("//span[text()='Соусы']/parent::div");
    private static final By FILLINGS_TAB = By.xpath("//span[text()='Начинки']/parent::div");

    // Активный таб
    private static final By ACTIVE_TAB = By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON)).click();
    }

    @Step("Ожидать отображение кнопки 'Оформить заказ'")
    public boolean waitForPlaceOrderButton() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(PLACE_ORDER_BUTTON))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить отображение кнопки 'Оформить заказ'")
    public boolean isPlaceOrderButtonDisplayed() {
        try {
            return driver.findElement(PLACE_ORDER_BUTTON).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Клик по табу 'Булки'")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(BUNS_TAB)).click();
    }

    @Step("Клик по табу 'Соусы'")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(SAUCES_TAB)).click();
    }

    @Step("Клик по табу 'Начинки'")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(FILLINGS_TAB)).click();
    }

    @Step("Получить текст активного таба")
    public String getActiveTabText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ACTIVE_TAB)).getText();
    }
}