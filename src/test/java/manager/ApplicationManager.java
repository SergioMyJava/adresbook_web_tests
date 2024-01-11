package manager;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {
    protected static WebDriver driver;

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
            new LoginHelper(driver);
        }
    }

    protected boolean elementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    protected void createGroup(GroupHelper newGroup) {
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(newGroup.name());
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(newGroup.header());
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(newGroup.footer());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    protected void createUserInAdressbook(UserHelper newUser) {
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(newUser.getFirstname());
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).sendKeys(newUser.getMiddlename());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(newUser.getLastname());
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).sendKeys(newUser.getNickname());
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).sendKeys(newUser.getTitle());
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).sendKeys(newUser.getCompany());
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys(newUser.getAddress());
        driver.findElement(By.name("home")).click();
        driver.findElement(By.name("home")).sendKeys(newUser.getHome());
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys(newUser.getMobile());
        driver.findElement(By.name("work")).click();
        driver.findElement(By.name("work")).sendKeys(newUser.getWork());
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).sendKeys(newUser.getFax());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.name("email")).sendKeys(newUser.getEmail());
        driver.findElement(By.name("email2")).click();
        driver.findElement(By.name("email2")).sendKeys(newUser.getEmail2());
        driver.findElement(By.name("email3")).click();
        driver.findElement(By.name("email3")).sendKeys(newUser.getEmail3());
        driver.findElement(By.name("homepage")).click();
        driver.findElement(By.name("homepage")).sendKeys("Home page");
        driver.findElement(By.cssSelector("input:nth-child(75)")).click();

//        {
//            WebElement dropdown = driver.findElement(By.name("bday"));
//            dropdown.findElement(By.xpath("//option[. = '1']")).click();
//        }
//        driver.findElement(By.name("bmonth")).click();
//        {
//            WebElement dropdown = driver.findElement(By.name("bmonth"));
//            dropdown.findElement(By.xpath("//option[. = 'January']")).click();
//        }
//        driver.findElement(By.name("byear")).click();
//        driver.findElement(By.name("byear")).sendKeys("1990");
//        driver.findElement(By.cssSelector("input:nth-child(75)")).click();
//        driver.findElement(By.linkText("home page")).click();
    }


    protected void openGroupsPage() {
        if (!elementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
    }

    protected void openAddNewPage() {
        if (!elementPresent(By.name("add new"))) {
            driver.findElement(By.linkText("add new")).click();
        }
    }

    protected boolean isGroupPresent() {
        return elementPresent(By.name("selected[]"));
    }

    protected boolean isUserPresent() {
        return elementPresent(By.name("selected[]"));
    }

    protected void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    protected void removeUser() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.xpath("//input[@value='Delete']")).click();
    }
}
