package manager;


import model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserHelper {
    private final ApplicationManager manager;

    public UserHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public void createUserInAdressbook(UserData newUser) {
        openAddNewPage();
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

    public void modifyUser(UserData user, UserData modifayUser) {
        initUserModifikationById(Integer.parseInt(user.id()));
        fillUserForm(modifayUser);
        submitUserModifikation();
    }

    public void removeUser(UserData user) {
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.xpath("//input[@value='Delete']")).click();
    }

//    public void removeAllUsers() {
//        returnToHomePage();
//        List<WebElement> checkboxes = manager.driver.findElements(By.xpath("//input[contains(@name,'selected[]')]"));
//        for (var chekcbox : checkboxes) {
//            chekcbox.click();
//        }
//        click(By.name("delete"));
//    }

    public void removeAllUser() {
        List<WebElement> checkboxes = manager.driver.findElements(By.xpath("//input[contains(@name,'selected[]')]"));
        for (var chekcbox : checkboxes) {
            chekcbox.click();
        }
        click(By.xpath("//input[@value='Delete']"));
        returnToHomePage();
    }

    private void fillUserForm(UserData user) {
        type(By.name("lastname"), user.getLastname());
        type(By.name("firstname"), user.getFirstname());
        type(By.name("middlename"), user.getMiddlename());
        type(By.name("address"), user.getAddress());
        type(By.name("mobile"), user.getMobile());
    }

    private void fillUserFirstLastName(UserData user) {
        type(By.name("firstname"), user.getFirstname());
        type(By.name("lastname"), user.getLastname());
    }

    public void fillUserFirstLastNamePhoto(UserData user) {
        openAddNewPage();
        type(By.name("firstname"), user.getFirstname());
        type(By.name("lastname"), user.getLastname());
        attach(By.name("photo"), user.getPhoto());
        click(By.xpath("//input[@value='Enter']"));

    }

    private void initUserModifikationById(int id) {
        manager.driver.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id))).click();
    }

    private void initUserModifikationFirstElement(UserData user) {
        manager.driver.findElement(By.xpath("//a[contains(@href,'edit.php?id=')]")).click();

//        List<WebElement> elements = manager.driver.findElements(By.xpath("//a[contains(@href,'edit.php?id=')]")); //код для клика по последнему элементу
//        elements.get(elements.size()-1).click();

    }

    public int getCountUser() {
        returnToHomePage();
        List<WebElement> elements = manager.driver.findElements(By.xpath("//a[contains(@href,'edit.php?id=')]"));
        return elements.size();
    }

    public void openAddNewPage() {
        if (!manager.elementPresent(By.name("add new"))) {
            manager.driver.findElement(By.linkText("add new")).click();
        }
    }

    private void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    private void attach(By locator, String file) {
        manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    private void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    private void submitUserModifikation() {
        manager.driver.findElement(By.xpath("//input[@value='Update']")).click();
    }

    private void returnToHomePage() {
        manager.driver.findElement(By.linkText("home")).click();
    }


    public List<UserData> getList() {
        returnToHomePage();
        var users = new ArrayList<UserData>();
        var spans = manager.driver.findElements(By.xpath("//tr[@name = 'entry']"));
        for (var span : spans) {
            var id = span.findElement(By.xpath("./td[1]/input")).getAttribute("id");
            var lastname = span.findElement(By.xpath("./td[2]")).getText();
            var firstname = span.findElement(By.xpath("./td[3]")).getText();
            var adress = span.findElement(By.xpath("./td[4]")).getText();
            var mobile = span.findElement(By.xpath("./td[6]")).getText();
            users.add(new UserData().userWithFullNameAdressMobile(firstname, lastname, adress, mobile).withId(id));
        }
        return users;
    }

//    public int getIdByIndex(int id, List newUsersList){
//        for(var user : newUsersList){
//            if(user.findElement(By.xpath("./td[1]/input")).getAttribute("id")){}
//        }
//    }

    public static String randomFile(String dir) {
        var fileName = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileName.length);
        return Paths.get(dir, fileName[index]).toString();
    }
}
