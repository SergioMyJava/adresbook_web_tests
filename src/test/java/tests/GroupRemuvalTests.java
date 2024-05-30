package tests;

import common.CommonFunction;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class GroupRemuvalTests extends TasteBase {

    @Test
    public void canRemoveGroup() {
        if (app.hmb().getGroupCount() == 0) {
            app.hmb().createGroup(new GroupData(""
                    , CommonFunction.randomstring(10)
                    , CommonFunction.randomstring(10)
                    , CommonFunction.randomstring(10)));
        }
        var oldGroups = app.hmb().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.getGroupHelper().removeGroup(oldGroups.get(index));
        var newGrous = app.hmb().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGrous, expectedList);
    }

    @Test
    public void canRemoveAllGroups() {
        if (app.hmb().getGroupCount() == 0) {
            app.getGroupHelper().createGroup(new GroupData(""
                    ,CommonFunction.randomstring(10)
                    ,CommonFunction.randomstring(10)
                    ,CommonFunction.randomstring(10)));
        }

        app.getGroupHelper().removeAllGroups();
        var oldGroups = app.getJdbsHelper().getGroupeList();
        Assertions.assertEquals(oldGroups.size(), 0);
    }
}
