import org.junit.jupiter.api.Test;


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
