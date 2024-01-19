package manager;


import model.UserData;
import org.openqa.selenium.By;

public class UserHelper {
    private final ApplicationManager manager;

    public UserHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public void createUserInAdressbook(UserData newUser) {
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(newUser.getFirstname());
        manager.driver.findElement(By.name("middlename")).click();
        manager.driver.findElement(By.name("middlename")).sendKeys(newUser.getMiddlename());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(newUser.getLastname());
        manager.driver.findElement(By.name("nickname")).click();
        manager.driver.findElement(By.name("nickname")).sendKeys(newUser.getNickname());
        manager.driver.findElement(By.name("title")).click();
        manager.driver.findElement(By.name("title")).sendKeys(newUser.getTitle());
        manager.driver.findElement(By.name("company")).click();
        manager.driver.findElement(By.name("company")).sendKeys(newUser.getCompany());
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys(newUser.getAddress());
        manager.driver.findElement(By.name("home")).click();
        manager.driver.findElement(By.name("home")).sendKeys(newUser.getHome());
        manager.driver.findElement(By.name("mobile")).click();
        manager.driver.findElement(By.name("mobile")).sendKeys(newUser.getMobile());
        manager.driver.findElement(By.name("work")).click();
        manager.driver.findElement(By.name("work")).sendKeys(newUser.getWork());
        manager.driver.findElement(By.name("fax")).click();
        manager.driver.findElement(By.name("fax")).sendKeys(newUser.getFax());
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.cssSelector("body")).click();
        manager.driver.findElement(By.name("email")).sendKeys(newUser.getEmail());
        manager.driver.findElement(By.name("email2")).click();
        manager.driver.findElement(By.name("email2")).sendKeys(newUser.getEmail2());
        manager.driver.findElement(By.name("email3")).click();
        manager.driver.findElement(By.name("email3")).sendKeys(newUser.getEmail3());
        manager.driver.findElement(By.name("homepage")).click();
        manager.driver.findElement(By.name("homepage")).sendKeys("Home page");
        manager.driver.findElement(By.cssSelector("input:nth-child(75)")).click();

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

    public void openAddNewPage() {
        if (!manager.elementPresent(By.name("add new"))) {
            manager.driver.findElement(By.linkText("add new")).click();
        }
    }

    public boolean isUserPresent() {
        return manager.elementPresent(By.name("selected[]"));
    }

    public void removeUser() {
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.xpath("//input[@value='Delete']")).click();
    }


    public void modifyUser(UserData userWithFullNameAdressMobile) {
        initUserModifikationFirstElement();
        fillUserForm(userWithFullNameAdressMobile);
        submitUserModifikation();
    }

    private void submitUserModifikation() {
        manager.driver.findElement(By.xpath("//input[@value='Update']")).click();
    }

    private void fillUserForm(UserData user) {
        type(By.name("firstname"), user.getFirstname());
        type(By.name("middlename"), user.getMiddlename());
        type(By.name("address"), user.getAddress());
        type(By.name("mobile"), user.getMobile());
    }

    private void initUserModifikationById(int id) {
        manager.driver.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id)));
    }

    private void initUserModifikationFirstElement() {
        manager.driver.findElement(By.xpath("//a[contains(@href,'edit.php?id=')]")).click();

//        List<WebElement> elements = manager.driver.findElements(By.xpath("//a[contains(@href,'edit.php?id=')]")); //код для клика по последнему элементу
//        elements.get(elements.size()-1).click();

    }


    private void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    private void click(By locator) {
        manager.driver.findElement(locator).click();
    }
}
