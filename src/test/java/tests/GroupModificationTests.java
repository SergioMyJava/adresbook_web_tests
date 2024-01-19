package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TasteBase {

    @Test
    void canModifayGroup() {
        if (!app.getGroupHelper().isGroupPresent()) {
            app.getGroupHelper().createGroup(new GroupData("", "", ""));
        }
        app.getGroupHelper().modifyGroup(new GroupData().withName("ModifyName"));
    }
}
