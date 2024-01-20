package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper {
    private final ApplicationManager manager;

    public GroupHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public void removeGroup() {
        openGroupsPage();
        selectGroup();
        click(By.name("delete"));
        returnToGroupsPage();
    }

    public void createGroup(GroupData newGroup) {
        openGroupsPage();
        initGroupeKreation();
        fillGroupForm(newGroup);
        click(By.name("submit"));
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData modifydGroup) {
        openGroupsPage();
        selectGroup();
        initGroupModifikation();
        fillGroupForm(modifydGroup);
        submitGroupModifikation();
        returnToGroupsPage();
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    private void initGroupeKreation() {
        click(By.name("new"));
    }


    private void returnToGroupsPage() {
        manager.driver.findElement(By.linkText("group page")).click();
    }

    private void submitGroupModifikation() {
        manager.driver.findElement(By.xpath("//input[@value='Update']")).click();
    }


    private void initGroupModifikation() {
        manager.driver.findElement(By.xpath("//input[@value='Edit group']")).click();
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }


    public void openGroupsPage() {
        if (!manager.elementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    public boolean isGroupPresent() {
        return manager.elementPresent(By.name("selected[]"));
    }

    private void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }
}
