import manager.ApplicationManager;
import manager.GroupHelper;
import org.junit.jupiter.api.Test;


public class GroupRemuvalTests extends ApplicationManager {

    @Test
    public void canRemoveGroup() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup(new GroupHelper("", "", ""));
        }
        removeGroup();
    }


}
