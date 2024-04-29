package tests;

import common.CommonFunction;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TasteBase {

    @Test
    void canModifayGroup() {
        if (app.hmb().getGroupCount() == 0) {
            app.getGroupHelper().createGroup(new GroupData(""
                    ,CommonFunction.randomstring(10)
                    ,CommonFunction.randomstring(10)
                    ,CommonFunction.randomstring(10)));
        }
        var oldGroups = app.getJdbsHelper().getGroupeList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var modifyGroup = new GroupData().withName(CommonFunction.randomstring(10));
        app.getGroupHelper().modifyGroup(oldGroups.get(index), modifyGroup);
        var newGroups = app.getJdbsHelper().getGroupeList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index,modifyGroup.withId(oldGroups.get(index).id()));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }
}
