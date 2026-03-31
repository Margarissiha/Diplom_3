package ui.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    @Step("Создание WebDriver для браузера: {browser}")
    public static WebDriver createDriver(String browser) {
        try {
            WebDriver driver;

            switch (browser.toLowerCase()) {
                case "yandex":
                    driver = createYandexDriver();
                    break;
                case "chrome":
                default:
                    driver = createChromeDriver();
                    break;
            }

            driver.manage().window().maximize();
            return driver;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create WebDriver for browser: " + browser, e);
        }
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        String os = System.getProperty("os.name").toLowerCase();
        String yandexPath;

        if (os.contains("win")) {
            String localAppData = System.getenv("LOCALAPPDATA");
            yandexPath = localAppData + "/Yandex/YandexBrowser/Application/browser.exe";
        } else if (os.contains("mac")) {
            yandexPath = "/Applications/Yandex.app/Contents/MacOS/Yandex";
        } else {
            yandexPath = "/usr/bin/yandex-browser";
        }

        options.setBinary(yandexPath);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        return new ChromeDriver(options);
    }
}