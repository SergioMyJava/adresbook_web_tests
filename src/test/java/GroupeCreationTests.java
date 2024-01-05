import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupeCreationTests extends TestBase {


    @Test
    public void createNewGroupe() {
        openGroupsPage();

        createGroup("next_test_group", "Group header", "groupe footer");

    }

    @Test
    public void createNewGroupeWithEmptyName() {
        openGroupsPage();
        createGroup("", "", "");

    }

    @Test
    public void deleteNewGroupeWithEmptyName() {
        if (!elementPresent(By.name("next_test_group"))) {
            createNewGroupe();
        }
    }
}
