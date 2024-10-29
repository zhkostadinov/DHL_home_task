package page_objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public final class Base {
    private static class LazyHolder {
        private static final Base INSTANCE = new Base();
    }

    private static WebDriver driver;
    private final Properties prop = new Properties();

    public static Base getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void initProperties() throws IOException {
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "//src//resources//data.properties");
        prop.load(fis);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public Properties getProp() {
        return this.prop;
    }

    public void initializeDriver() {
        String browserName = this.getProp().getProperty("browser");
        if (browserName.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else if (browserName.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
