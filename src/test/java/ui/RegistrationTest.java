package ui;

import api.client.UserClient;
import api.model.User;
import api.model.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ui.page.LoginPage;
import ui.page.RegisterPage;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;

@DisplayName("Тесты регистрации")
@RunWith(Parameterized.class)
public class RegistrationTest extends BaseUiTest {

    private final String password;
    private final boolean shouldSucceed;
    private UserClient userClient;
    private String accessToken;

    public RegistrationTest(String password, boolean shouldSucceed) {
        this.password = password;
        this.shouldSucceed = shouldSucceed;
    }

    @Parameterized.Parameters(name = "Пароль: {0} -> Успех: {1}")
    public static Object[][] testData() {
        return new Object[][]{
                {"123456", true},
                {"1234567", true},
                {"12345", false},
                {"123", false},
                {"", false}
        };
    }

    @After
    public void tearDown() {
        if (accessToken != null && userClient != null) {
            userClient.deleteUser(accessToken);
        }
        super.tearDown();
    }

    @Test
    @DisplayName("Проверка регистрации с разными паролями")
    @Description("Проверяем успешную регистрацию (пароль 6+ символов) и ошибку (пароль меньше 6 символов)")
    public void testRegistrationWithDifferentPasswords() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        String name = generateRandomName();
        String email = generateRandomEmail();

        registerPage.open();
        registerPage.register(name, email, password);

        if (shouldSucceed) {
            boolean isLoginPageDisplayed = loginPage.waitForPageLoad();
            assertTrue("Должен быть редирект на страницу логина", isLoginPageDisplayed);

            userClient = new UserClient();
            User tempUser = new User(email, password, name);
            ValidatableResponse loginResponse = userClient.loginUser(UserData.fromUser(tempUser));

            int statusCode = loginResponse.extract().statusCode();
            if (statusCode == SC_OK) {
                accessToken = loginResponse.extract().path("accessToken");
            }

            assertEquals("Пользователь должен успешно логиниться после регистрации",
                    SC_OK, statusCode);

        } else {
            boolean isErrorDisplayed = registerPage.isErrorMessageDisplayed();
            assertTrue("Должна отображаться ошибка о некорректном пароле",
                    isErrorDisplayed);
        }
    }
}