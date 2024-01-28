package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TasteBase {

    @Test
    void canModifayGroup() {
        if (app.getGroupHelper().getCount() == 0) {
            app.getGroupHelper().createGroup(new GroupData("", "", "", ""));
        }
        var oldGroups = app.getGroupHelper().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var modifyGroup = new GroupData().withName("ModifyName");
        app.getGroupHelper().modifyGroup(oldGroups.get(index), modifyGroup);
        var newGroups = app.getGroupHelper().getList();
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
