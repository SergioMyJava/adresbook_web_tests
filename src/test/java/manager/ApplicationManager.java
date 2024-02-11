package manager;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    private LogHelper session;
    private GroupHelper group;
    public UserHelper user;
    protected WebDriver driver;
    private Properties properties;
    private JdbsHelper jdbs;

    @BeforeEach
    public void init(String browser, Properties properties) {
        this.properties = properties;
        if (driver == null) {
            if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            }
            if (driver == null) {
                if ("chrome".equals(browser)) {
                    driver = new ChromeDriver();
                } else {
                    throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
                }
            }

            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.braserurl"));
            driver.manage().window().setSize(new Dimension(968, 728));
            session().login(properties.getProperty("war.username"), properties.getProperty("war.password"));

        }
    }

    public LogHelper session() {
        if (session == null) {
            session = new LogHelper(this);
        }
        return session;
    }

    public GroupHelper getGroupHelper() {
        if (group == null) {
            group = new GroupHelper(this);
        }
        return group;
    }

    public UserHelper getUserHelper() {
        if (user == null) {
            user = new UserHelper(this);
        }
        return user;
    }


    public JdbsHelper getJdbsHelper() {
        if (jdbs == null) {
            jdbs = new JdbsHelper(this);
        }
        return jdbs;
    }

    public boolean elementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

}
