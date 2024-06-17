package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String browser;
    private Properties properties;
    private SessionHelper session;

    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;

    }

    public WebDriver driver(){
        if(driver == null){
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
            //session().login(properties.getProperty("war.username"), properties.getProperty("war.password"));
        }
        return driver;
    }

    public SessionHelper session(){
        if(session==null){
            session = new SessionHelper(this);
        }
        return session;
    }
}
