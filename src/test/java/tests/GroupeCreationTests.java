package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupeCreationTests extends TasteBase {


    public static List<GroupData> groupProvaider() {
        var result = new ArrayList<GroupData>();
        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData("", name, header, footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new GroupData()
                    .withHeader(randomstring(i * 10))
                    .withFooter(randomstring(i * 10))
                    .withName(randomstring(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvaider")
    public void createMultiplyGroupe(GroupData group) {
        var oldGroups = app.getGroupHelper().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        //app.getGroupHelper().removeGroup(oldGroups.get(index));
        var newGrous = app.getGroupHelper().getList();
        var expectedList = new ArrayList<>(oldGroups);
        app.getGroupHelper().createGroup(group);
        Assertions.assertEquals(newGrous, expectedList);

    }

    public static List<GroupData> negativeGroupProvaider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "next_test_group'", "Group header", "groupe footer")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvaider")
    public void canNotCreateGroupe(GroupData group) {
        int countGroups = app.getGroupHelper().getCount();
        app.getGroupHelper().createGroup(group);
        int newCountGroups = app.getGroupHelper().getCount();
        Assertions.assertEquals(countGroups, newCountGroups);

    }
}
