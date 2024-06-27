package driver;

import main.MainCalls;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (webDriver.get() == null) {
            webDriver.set( createDriver());
        }
        return webDriver.get();
    }

    private static WebDriver createDriver() {
        WebDriver driver = null;
        String browserType = getBrowserType();
        Boolean isHeadLess = launchBrowserAsHeadless();
        switch (browserType) {
            case "chrome": {
                ChromeOptions chromeOptions = new ChromeOptions();
                // Wait for the whole page to load
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (isHeadLess) {
                    chromeOptions.addArguments("--headless");
                }

                driver = new ChromeDriver(chromeOptions);
                break;
            }
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/java/driver/drivers/geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                // Wait for the whole page to load
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (isHeadLess) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    private static String getBrowserType() {
        String browserType = null;

        String browserTypeRemote = System.getProperty("browserType");
        try {
            if (browserTypeRemote == null || browserTypeRemote.isEmpty()) {

                Properties properties = new Properties();
                FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");

                properties.load(file);
                browserType = properties.getProperty("browser").toLowerCase().trim();
            } else {
                browserType = browserTypeRemote;
                return browserType;
            }

        } catch (IOException x) {
            System.out.println(x);
        }
        return browserType;
    }

    private static Boolean launchBrowserAsHeadless() {
        boolean isHeadLess = false;
        isHeadLess = Boolean.parseBoolean(System.getProperty("headless"));
        return isHeadLess;
    }

    public static void cleanUpDriver() {
        webDriver.get().quit();
        webDriver.remove();
        MainCalls.MakePageObjectsNull();
    }
}
