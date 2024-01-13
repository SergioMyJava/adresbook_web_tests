package manager;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {
    private LogHelper session;
    private GroupHelper group;
    public UserHelper user;
    protected WebDriver driver;

    @BeforeEach
    public void init() {
        if (driver == null) {
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(968, 728));
            session().login("admin","secret");
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

    public boolean elementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }


    public void removeUser() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.xpath("//input[@value='Delete']")).click();
    }


}
