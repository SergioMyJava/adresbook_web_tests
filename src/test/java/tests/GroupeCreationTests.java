package tests;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunction;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupeCreationTests extends TasteBase {


    public static List<GroupData> groupProvaider() throws IOException {
        var result = new ArrayList<GroupData>();
        var json = Files.readString(Paths.get("groups.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {
        });
        result.addAll(value);
        return result;
    }


    @ParameterizedTest
    @MethodSource("groupProvaider")
    public void createMultiplyGroupeInInterface(GroupData group) {
        var oldGroups = app.getGroupHelper().getList();
        app.getGroupHelper().createGroup(group);
        var newGroups = app.getGroupHelper().getList();

        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxID = newGroups.get(newGroups.size() - 1).id();
        var expectedList = new ArrayList<>(oldGroups);

        expectedList.add(group.withId(maxID).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

    public static List<GroupData> negativeGroupProvaider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData(""
                        , CommonFunction.randomstring(10)
                        , CommonFunction.randomstring(10)
                        , CommonFunction.randomstring(10))));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvaider")
    public void canNotCreateGroupe(GroupData group) {
        var oldGroups = app.getJdbsHelper().getGroupeList();
        app.getGroupHelper().createGroup(group);
        var newGroups = app.getJdbsHelper().getGroupeList();
        Assertions.assertEquals(newGroups, oldGroups);

    }

    public static List<GroupData> groupProvaiderForOne() throws IOException {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData(""
                        , CommonFunction.randomstring(10)
                        , CommonFunction.randomstring(10)
                        , CommonFunction.randomstring(10))));
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvaiderForOne")
    public void createOneGroupeFromSQL(GroupData group) {
        var oldGroups = app.getJdbsHelper().getGroupeList();
        app.getGroupHelper().createGroup(group);
        var newGroups = app.getJdbsHelper().getGroupeList();

        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxID = newGroups.get(newGroups.size() - 1).id();
        var expectedList = new ArrayList<>(oldGroups);

        expectedList.add(group.withId(maxID));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

    }

    @ParameterizedTest
    @MethodSource("groupProvaiderForOne")
    public void createOneGroupeFromHibernate(GroupData group) {
        var oldGroups = app.hmb().getGroupList();
        app.hmb().createGroup(group);
        var newGroups = app.hmb().getGroupList();

        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxID = newGroups.get(newGroups.size() - 1).id();
        var expectedList = new ArrayList<>(oldGroups);

        expectedList.add(group.withId(maxID));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

    }

}
