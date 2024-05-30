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
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupeCreationTests extends TasteBase {

    public static Stream<GroupData> groupProvaiderForOne() throws IOException {
        Supplier<GroupData> randomGroup=()-> new GroupData(""
                , CommonFunction.randomstring(10)
                , CommonFunction.randomstring(10)
                , CommonFunction.randomstring(10));
        return Stream.generate(randomGroup).limit(2);
    }


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
    @MethodSource("groupProvaiderForOne")
    public void createMultiplyGroupeInInterface(GroupData group) {
        var oldGroups = app.getGroupHelper().getList();
        app.getGroupHelper().createGroup(group);
        var newGroups = app.getGroupHelper().getList();

        List<GroupData> extraGroups = newGroups.stream()
                .filter(g -> !oldGroups.contains(g))
                .collect(Collectors.toList());

        var expectedGroup = extraGroups.get(0);


        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(expectedGroup.id()).withHeader(expectedGroup.getHeader()).withFooter(expectedGroup.getFooter()));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
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



    @ParameterizedTest
    @MethodSource("groupProvaiderForOne")
    public void createOneGroupeFromSQL(GroupData group) {
        var oldGroups = app.getJdbsHelper().getGroupeList();
        app.getGroupHelper().createGroup(group);
        var newGroups = app.getJdbsHelper().getGroupeList();
        var maxID = newGroups.get(newGroups.size() - 1).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxID));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }

    @ParameterizedTest
    @MethodSource("groupProvaiderForOne")
    public void createOneGroupeFromHibernate(GroupData group) {
        var oldGroups = app.hmb().getGroupList();
        app.hmb().createGroup(group);
        var newGroups = app.hmb().getGroupList();
        var maxID = newGroups.get(newGroups.size() - 1).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxID));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }
}
