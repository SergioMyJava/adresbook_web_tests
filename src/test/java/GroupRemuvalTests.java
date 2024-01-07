import model.GroupData;
import org.junit.jupiter.api.Test;


public class GroupRemuvalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup(new GroupData("", "", ""));
        }
        RemoveGroup();
    }


}
