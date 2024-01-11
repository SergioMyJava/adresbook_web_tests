package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;


public class LoginHelper {
    protected WebDriver driver;



    public LoginHelper(WebDriver driver){
        this.driver=driver;
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
        driver.get("http://localhost/addressbook/");
        driver.manage().window().setSize(new Dimension(968, 728));
        driver.findElement(By.name("user")).sendKeys("admin");
        driver.findElement(By.name("pass")).sendKeys("secret");
        driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        }
    }

//    public WebDriver getDriver(){
//        if (driver == null) {
//            driver = new ChromeDriver();
//            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
//            driver.get("http://localhost/addressbook/");
//            driver.manage().window().setSize(new Dimension(968, 728));
//            driver.findElement(By.name("user")).sendKeys("admin");
//            driver.findElement(By.name("pass")).sendKeys("secret");
//            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
//        }
//        return driver;
//    }
//}
