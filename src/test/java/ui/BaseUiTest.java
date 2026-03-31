package ui;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ui.driver.WebDriverFactory;

@DisplayName("Базовый класс для UI тестов")
public class BaseUiTest {

    protected WebDriver driver;
    protected static final String BROWSER = System.getProperty("browser", "chrome");

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver(BROWSER);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected String generateRandomEmail() {
        return "test" + System.currentTimeMillis() + "@example.com";
    }

    protected String generateRandomName() {
        return "User" + System.currentTimeMillis();
    }
}