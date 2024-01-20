package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupeCreationTests extends TasteBase {


    @Test
    public void createNewGroupe() {
        int countGroups = app.getGroupHelper().getCount();
        app.getGroupHelper().createGroup(new GroupData("next_test_group", "Group header", "groupe footer"));
        int newCountGroups = app.getGroupHelper().getCount();
        Assertions.assertEquals(countGroups+1,newCountGroups);
    }

    @Test
    public void createNewGroupeWithEmptyName() {
        int countGroups = app.getGroupHelper().getCount();
        app.getGroupHelper().createGroup(new GroupData("", "", ""));
        int newCountGroups = app.getGroupHelper().getCount();
        Assertions.assertEquals(countGroups+1,newCountGroups);
    }

    @Test
    public void deleteNewGroupeWithEmptyName() {
        if (!app.elementPresent(By.name("next_test_group"))) {
            createNewGroupe();
        }
    }

    @Test
    public void createNewGroupeWithNameOnly() {
        int countGroups = app.getGroupHelper().getCount();
        app.getGroupHelper().createGroup(new GroupData().withName("someName1"));
        int newCountGroups = app.getGroupHelper().getCount();
        Assertions.assertEquals(countGroups+1,newCountGroups);
    }
}
