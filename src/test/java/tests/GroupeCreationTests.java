package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupeCreationTests extends TasteBase {


    @Test
    public void createNewGroupe() {
        app.getGroupHelper().createGroup(new GroupData("next_test_group", "Group header", "groupe footer"));

    }

    @Test
    public void createNewGroupeWithEmptyName() {
        app.getGroupHelper().createGroup(new GroupData("", "", ""));

    }

    @Test
    public void deleteNewGroupeWithEmptyName() {
        if (!app.elementPresent(By.name("next_test_group"))) {
            createNewGroupe();
        }
    }

    @Test
    public void createNewGroupeWithNameOnly() {
        GroupData emptyGroupe = new GroupData();
        app.getGroupHelper().createGroup(new GroupData().withName("someName1"));
    }
}
