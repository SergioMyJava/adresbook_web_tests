package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;


public class GroupRemuvalTests  extends TasteBase {

    @Test
    public void canRemoveGroup() {
        app.getGroupHelper().openGroupsPage();
        if (!app.getGroupHelper().isGroupPresent()) {
            app.getGroupHelper().createGroup(new GroupData("", "", ""));
        }
        app.getGroupHelper().removeGroup();
    }


}
