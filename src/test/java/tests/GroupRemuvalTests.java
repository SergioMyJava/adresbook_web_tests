package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GroupRemuvalTests extends TasteBase {

    @Test
    public void canRemoveGroup() {
        if (app.getGroupHelper().getCount() == 0) {
            app.getGroupHelper().createGroup(new GroupData("group header", "group heder", "group footer"));
        }
        int countGroups = app.getGroupHelper().getCount();
        app.getGroupHelper().removeGroup();
        int newCountGroups = app.getGroupHelper().getCount();
        Assertions.assertEquals(countGroups-1,newCountGroups);
    }


}
