package ui;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ui.page.MainPage;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты конструктора бургера")
@RunWith(Parameterized.class)
public class ConstructorTest extends BaseUiTest {

    private final String tabName;
    private final String expectedText;

    public ConstructorTest(String tabName, String expectedText) {
        this.tabName = tabName;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters(name = "Переход к разделу: {0}")
    public static Object[][] testData() {
        return new Object[][]{
                {"Булки", "Булки"},
                {"Соусы", "Соусы"},
                {"Начинки", "Начинки"}
        };
    }

    @Test
    @DisplayName("Проверка переключения табов в конструкторе")
    @Description("Проверяем переход к разделам Булки, Соусы, Начинки")
    public void testConstructorTabs() {
        MainPage mainPage = new MainPage(driver);

        mainPage.open();

        switch (tabName) {
            case "Булки":
                mainPage.clickBunsTab();
                break;
            case "Соусы":
                mainPage.clickSaucesTab();
                break;
            case "Начинки":
                mainPage.clickFillingsTab();
                break;
        }

        String activeTabText = mainPage.getActiveTabText();
        assertTrue("Активный таб должен содержать " + tabName,
                activeTabText.contains(expectedText));
    }
}