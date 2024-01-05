import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


public class GroupRemuvalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup("", "", "");
        }
        RemoveGroup();
    }


}
