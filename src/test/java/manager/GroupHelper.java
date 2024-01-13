package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper {
    private final ApplicationManager manager;

    public GroupHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public void openGroupsPage() {
        if (!manager.elementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    public boolean isGroupPresent() {
        return manager.elementPresent(By.name("selected[]"));
    }

    public void removeGroup() {
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.name("delete")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }

    public void createGroup(GroupData newGroup) {
        manager.driver.findElement(By.name("new")).click();
        manager.driver.findElement(By.name("group_name")).click();
        manager.driver.findElement(By.name("group_name")).sendKeys(newGroup.name());
        manager.driver.findElement(By.name("group_header")).click();
        manager.driver.findElement(By.name("group_header")).sendKeys(newGroup.header());
        manager.driver.findElement(By.name("group_footer")).click();
        manager.driver.findElement(By.name("group_footer")).sendKeys(newGroup.footer());
        manager.driver.findElement(By.name("submit")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }


//    private final String name;
//    private final String header;
//    private final String footer;
//
//    public GroupHelper(String name, String header, String footer) {
//        this.name = name;
//        this.header = header;
//        this.footer = footer;
//    }
//
//    public GroupHelper() {
//        this("","","");
//    }
//
//    public String name() {
//        return name;
//    }
//
//    public String header() {
//        return header;
//    }
//
//    public String footer() {
//        return footer;
//    }
//
//    public GroupHelper withName(String someName) {
//        return new GroupHelper(someName, this.header,this.footer);
//    }
//
//    public GroupHelper withHeader(String someHeader) {
//        return new GroupHelper(this.name, someHeader,this.footer);
//    }
//
//    public GroupHelper withFooter(String someFooter) {
//        return new GroupHelper(this.name, this.header,someFooter);
//    }
}
