package tests;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunction;
import model.GroupData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupeCreationTests extends TasteBase {


    public static List<GroupData> groupProvaider() throws IOException {
        var result = new ArrayList<GroupData>();
//        for (var name : List.of("fresh group name", "group name")) {
//            for (var header : List.of("fresh group header", "fgroup header")) {
//                for (var footer : List.of("fresh group footer", "group footer")) {
//                    result.add(new GroupData()
//                            .withHeader(header)
//                            .withFooter(footer)
//                            .withName(name));
//                }
//            }
//        }
//        for (int i = 0; i < 2; i++) {
//            result.add(new GroupData()
//                    .withHeader(CommonFunction.randomstring(i * 10))
//                    .withFooter(CommonFunction.randomstring(i * 10))
//                    .withName(CommonFunction.randomstring(i * 10)));
//        }
        var json = Files.readString(Paths.get("groups.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {});
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvaider")
    public void createMultiplyGroupe(GroupData group) {
        var oldGroups = app.getGroupHelper().getList();
        app.getGroupHelper().createGroup(group);
        var newGroups = app.getGroupHelper().getList();

        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);

        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

    public static List<GroupData> negativeGroupProvaider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "next_test_group'",
                        "Group header", "groupe footer")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvaider")
    public void canNotCreateGroupe(GroupData group) {
        var oldGroups = app.getGroupHelper().getList();
        app.getGroupHelper().createGroup(group);
        var newGroups = app.getGroupHelper().getList();
        Assertions.assertEquals(newGroups, oldGroups);

    }
}
