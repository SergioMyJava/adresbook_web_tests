package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper {
    private final ApplicationManager manager;

    public GroupHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public void removeGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        click(By.name("delete"));
        returnToGroupsPage();
    }

    public void removeAllGroups() {
        openGroupsPage();
        List<WebElement> checkboxes = manager.driver.findElements(By.xpath("//input[contains(@name,'selected[]')]"));
        for (var chekcbox : checkboxes) {
            chekcbox.click();
        }
        click(By.name("delete"));
    }

    public void createGroup(GroupData newGroup) {
        openGroupsPage();
        initGroupeKreation();
        fillGroupForm(newGroup);
        click(By.name("submit"));
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData group, GroupData modifyName) {
        openGroupsPage();
        selectGroup(group);
        initGroupModifikation();
        fillGroupForm(modifyName);
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

    public void selectGroup(GroupData group) {
                click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }


    public void openGroupsPage() {
        if (!manager.elementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    private void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getList() {
        openGroupsPage();
        var groups = new ArrayList<GroupData>();
        var spans = manager.driver.findElements(By.xpath("//span[@class = 'group']"));
        for (var span : spans) {
            var name = span.getText();
            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
