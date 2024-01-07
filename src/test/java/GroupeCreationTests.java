import model.GroupData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupeCreationTests extends TestBase {


    @Test
    public void createNewGroupe() {
        openGroupsPage();

        createGroup(new GroupData("next_test_group", "Group header", "groupe footer"));

    }

    @Test
    public void createNewGroupeWithEmptyName() {
        openGroupsPage();
        createGroup(new GroupData("", "", ""));

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
        GroupData emptyGroupe = new GroupData() ;
        GroupData groupeWithName = new GroupData().withName("someName") ;
        createGroup(groupeWithName);
    }
}
