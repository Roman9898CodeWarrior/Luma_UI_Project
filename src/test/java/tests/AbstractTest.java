package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;
import utils.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.selenide.AllureSelenide;
import pages.home.HomePage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractTest {
    private static final String PROPERTIES_FILE_PATH = "test.properties";
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);
    private static final Dotenv dotenv = Dotenv.configure().filename(".env-test").load();
    private HomePage homePage;
    private Properties properties;

    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 60000;
        Configuration.pageLoadTimeout = 60000;
    }

    @BeforeEach
    @DisplayName("Set up driver and navigate to home page")
    public void setUp() {
        initializeUser();
        setUpDriver();
        Configuration.browser = properties.getProperty("browser", "chrome");
        homePage = Selenide.open(properties.getProperty("url"), HomePage.class);
    }


    @AfterAll
    public static void tearDownAll() {
        SelenideLogger.removeListener("AllureSelenide");
    }

    private void setUpDriver() {
        try (InputStream input = AbstractTest.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_PATH)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            LOGGER.error("Failed to read properties file! Path: {}, Error: {}", PROPERTIES_FILE_PATH, e.getMessage(), e);
            throw new RuntimeException("Failed to read properties file!", e);
        }
    }

    private void initializeUser() {
        User.initialize(dotenv);
    }

    @AfterEach
    void closeDriver() {
        Selenide.closeWebDriver();
    }
}
