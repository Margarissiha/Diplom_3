package ui;

import api.client.UserClient;
import api.model.User;
import api.model.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ui.page.*;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты входа в систему")
public class LoginTest extends BaseUiTest {

    private String testEmail;
    private String testPassword;
    private String testName;
    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        super.setUp();

        userClient = new UserClient();

        testEmail = generateRandomEmail();
        testPassword = "1234567";
        testName = generateRandomName();

        user = new User(testEmail, testPassword, testName);
        ValidatableResponse createResponse = userClient.createUser(user);
        accessToken = createResponse.extract().path("accessToken");

        if (accessToken == null) {
            UserData credentials = UserData.fromUser(user);
            ValidatableResponse loginResponse = userClient.loginUser(credentials);
            accessToken = loginResponse.extract().path("accessToken");
        }
    }

    @After
    public void tearDown() {
        if (accessToken != null && userClient != null) {
            userClient.deleteUser(accessToken);
        }
        super.tearDown();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка входа через кнопку на главной странице")
    public void testLoginViaMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(testEmail, testPassword);

        assertTrue("Пользователь должен быть авторизован",
                mainPage.waitForPlaceOrderButton());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка входа через кнопку в хедере")
    public void testLoginViaPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        mainPage.open();
        headerPage.clickPersonalAccount();
        loginPage.login(testEmail, testPassword);

        assertTrue("Пользователь должен быть авторизован",
                mainPage.waitForPlaceOrderButton());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через ссылку 'Войти' на странице регистрации")
    public void testLoginViaRegisterForm() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        registerPage.open();
        registerPage.clickLoginLink();
        loginPage.waitForPageLoad();
        loginPage.login(testEmail, testPassword);

        MainPage mainPage = new MainPage(driver);
        assertTrue("Пользователь должен быть авторизован",
                mainPage.waitForPlaceOrderButton());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через ссылку 'Войти' на странице восстановления пароля")
    public void testLoginViaForgotPasswordForm() {
        PasswordPage forgotPasswordPage = new PasswordPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        forgotPasswordPage.open();
        forgotPasswordPage.clickLoginLink();
        loginPage.waitForPageLoad();
        loginPage.login(testEmail, testPassword);

        MainPage mainPage = new MainPage(driver);
        assertTrue("Пользователь должен быть авторизован",
                mainPage.waitForPlaceOrderButton());
    }
}