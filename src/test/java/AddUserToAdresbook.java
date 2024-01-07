import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class AddUserToAdresbook extends TestBase{

  @Test
  public void createNewUser() {
    driver.get("http://localhost/addressbook/addressbook/");
    driver.manage().window().setSize(new Dimension(1936, 1056));
    driver.findElement(By.linkText("Logout")).click();
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.cssSelector("input:nth-child(7)")).click();
    driver.findElement(By.linkText("add new")).click();
    driver.findElement(By.name("firstname")).click();
    driver.findElement(By.name("firstname")).sendKeys("First Name");
    driver.findElement(By.name("middlename")).click();
    driver.findElement(By.name("middlename")).sendKeys("Midle name");
    driver.findElement(By.name("lastname")).click();
    driver.findElement(By.name("lastname")).sendKeys("Last name");
    driver.findElement(By.name("nickname")).click();
    driver.findElement(By.name("nickname")).sendKeys("Nickname");
    driver.findElement(By.name("title")).click();
    driver.findElement(By.name("title")).sendKeys("Title");
    driver.findElement(By.name("company")).click();
    driver.findElement(By.name("company")).sendKeys("Company");
    driver.findElement(By.name("address")).click();
    driver.findElement(By.name("address")).sendKeys("Adress");
    driver.findElement(By.name("home")).click();
    driver.findElement(By.name("home")).sendKeys("Home");
    driver.findElement(By.name("mobile")).click();
    driver.findElement(By.name("mobile")).sendKeys("Mobile");
    driver.findElement(By.name("work")).click();
    driver.findElement(By.name("work")).sendKeys("Work");
    driver.findElement(By.name("fax")).click();
    driver.findElement(By.name("fax")).sendKeys("Fax");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.name("email")).sendKeys("Email");
    driver.findElement(By.name("email2")).click();
    driver.findElement(By.name("email2")).sendKeys("Email");
    driver.findElement(By.name("email3")).click();
    driver.findElement(By.name("email3")).sendKeys("Email");
    driver.findElement(By.name("email2")).click();
    driver.findElement(By.name("email2")).sendKeys("Email2");
    driver.findElement(By.name("email3")).click();
    driver.findElement(By.name("email3")).sendKeys("Email3");
    driver.findElement(By.name("homepage")).click();
    driver.findElement(By.name("homepage")).sendKeys("Home page");
    driver.findElement(By.name("bday")).click();
    {
      WebElement dropdown = driver.findElement(By.name("bday"));
      dropdown.findElement(By.xpath("//option[. = '1']")).click();
    }
    driver.findElement(By.name("bmonth")).click();
    {
      WebElement dropdown = driver.findElement(By.name("bmonth"));
      dropdown.findElement(By.xpath("//option[. = 'January']")).click();
    }
    driver.findElement(By.name("byear")).click();
    driver.findElement(By.name("byear")).sendKeys("1990");
    driver.findElement(By.cssSelector("input:nth-child(75)")).click();
    driver.findElement(By.linkText("home page")).click();
    driver.findElement(By.linkText("Logout")).click();
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).sendKeys("secret");
  }
}
