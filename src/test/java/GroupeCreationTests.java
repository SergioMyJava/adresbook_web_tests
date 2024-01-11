import manager.ApplicationManager;
import manager.GroupHelper;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupeCreationTests extends ApplicationManager {


    @Test
    public void createNewGroupe() {
        openGroupsPage();

        createGroup(new GroupHelper("next_test_group", "Group header", "groupe footer"));

    }

    @Test
    public void createNewGroupeWithEmptyName() {
        openGroupsPage();
        createGroup(new GroupHelper("", "", ""));

    }

    @Test
    public void deleteNewGroupeWithEmptyName() {
        if (!elementPresent(By.name("next_test_group"))) {
            createNewGroupe();
        }
    }

    @Test
    public void createNewGroupeWithNameOnly() {
        openGroupsPage();
        GroupHelper emptyGroupe = new GroupHelper() ;
        GroupHelper groupeWithName = new GroupHelper().withName("someName1") ;
        createGroup(groupeWithName);
    }
}
