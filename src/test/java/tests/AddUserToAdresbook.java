package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunction;
import model.GroupData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class AddUserToAdresbook extends TasteBase {


    public static List<UserData> userProvaider() throws IOException {
        var result = new ArrayList<UserData>();
        var json = Files.readString(Paths.get("contakts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<UserData>>() {
        });
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("userProvaider")
    public void createMultiplyUsers(UserData user) throws InterruptedException {
        var oldUsersList = app.getJdbsHelper().getUserList();
        app.getUserHelper().createUserInAdressbook(user);
        Thread.sleep(3000);
        var newUsersList = app.getJdbsHelper().getUserList();

        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUsersList.sort(compareById);

        var expectedList = new ArrayList<>(oldUsersList);

        var maxID = newUsersList.get(newUsersList.size() - 1).id();
        expectedList.add(user.withId(maxID).
                withFirstnameLastname(user.getFirstname(), user.getLastname()));

        expectedList.sort(compareById);

        Assertions.assertEquals(newUsersList, expectedList);
    }

    @Test
    public void newUserAlmoustInformation() {
        var oldUsersList = app.getJdbsHelper().getUserList();
        app.getUserHelper().openAddNewPage();
        var newUser = new UserData("", CommonFunction.randomstring(10), CommonFunction.randomstring(10),
                CommonFunction.randomstring(10), CommonFunction.randomstring(10),
                CommonFunction.randomstring(10), CommonFunction.randomstring(10),
                CommonFunction.randomstring(10) + " " + CommonFunction.randomNumber(10), "",
                CommonFunction.randomNumber(10) + " " + CommonFunction.randomNumber(10),
                CommonFunction.randomstring(10), CommonFunction.randomNumber(10) + " "
                + CommonFunction.randomNumber(10), CommonFunction.randomstring(10),
                CommonFunction.randomstring(10), CommonFunction.randomstring(10),
                CommonFunction.randomstring(10), CommonFunction.randomstring(10), "", "");
        app.getUserHelper().createUserInAdressbook(newUser);
        var newUsersList = app.getJdbsHelper().getUserList();
        oldUsersList.add(newUser.withId(newUsersList.get(newUsersList.size() - 1).id()));
        Assertions.assertEquals(newUsersList, oldUsersList);
    }

    @Test
    public void newUserWithFirstNameLastnamePhoto() {
        app.getUserHelper().openAddNewPage();
        var newUser = new UserData().withFirstnameLastname(
                CommonFunction.randomstring(10), CommonFunction.randomstring(10));
        app.getUserHelper().fillUserFirstLastNamePhoto(newUser);
    }

    public static List<UserData> oneUser() throws IOException {
        var result = new ArrayList<UserData>(List.of(
                new UserData().userWithFullNameAdressMobile(CommonFunction.randomstring(10),
                        CommonFunction.randomstring(10), CommonFunction.randomstring(10),
                        CommonFunction.randomstring(10))));
        return result;
    }

    @ParameterizedTest
    @MethodSource("oneUser")
    public void newUserWithSQL(UserData user) {
        var oldUsersList = app.getJdbsHelper().getUserList();
        app.getUserHelper().openAddNewPage();
        app.getUserHelper().createUserInAdressbook(user);

        var newUsersList = app.getJdbsHelper().getUserList();

        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUsersList.sort(compareById);

        var expectedList = new ArrayList<>(oldUsersList);
        var maxID = newUsersList.get(newUsersList.size() - 1).id();
        expectedList.add(user.withId(maxID));//.withFirstnameLastname(user.getFirstname(), user.getLastname())

        expectedList.sort(compareById);

        Assertions.assertEquals(newUsersList, expectedList);

    }

    @ParameterizedTest
    @MethodSource("oneUser")
    public void newUserWithHibernate(UserData user) {
        var oldUsersList = app.hmb().getUserList();
        app.getUserHelper().openAddNewPage();
        app.getUserHelper().createUserInAdressbook(user);

        var newUsersList = app.hmb().getUserList();

        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUsersList.sort(compareById);

        var expectedList = new ArrayList<>(oldUsersList);
        var maxID = newUsersList.get(newUsersList.size() - 1).id();
        expectedList.add(user.withId(maxID));//.withFirstnameLastname(user.getFirstname(), user.getLastname())

        expectedList.sort(compareById);

        Assertions.assertEquals(newUsersList, expectedList);

    }

    @ParameterizedTest
    @MethodSource("oneUser")
    public void newUserWithHibernateWithGroup(UserData user) {
        var oldUsersList = app.hmb().getUserList();

        if (app.hmb().getGroupCount() == 0) {
            app.getGroupHelper().createGroup(new GroupData(""
                    , CommonFunction.randomstring(10)
                    , CommonFunction.randomstring(10)
                    , CommonFunction.randomstring(10)));
        }
        var group = app.hmb().getGroupList().get(0);
        var oldRelated = app.hmb().getUsersInGroup(group);
        app.getUserHelper().createUserWithGroup(user, group);
        var newRelated = app.hmb().getUsersInGroup(group);

        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        oldRelated.sort(compareById);
        newRelated.sort(compareById);

        var maxID = newRelated.get(newRelated.size() - 1).id();
        oldRelated.add(user.withId(maxID));
        Assertions.assertEquals(oldRelated, newRelated);
    }

    public static List<GroupData> groupProvaiderForOne() throws IOException {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData(""
                        , CommonFunction.randomstring(10)
                        , CommonFunction.randomstring(10)
                        , CommonFunction.randomstring(10))));
        return result;
    }

    @Test
    public void addUserToGroup() throws InterruptedException {
        UserData user = new UserData().userWithFullNameAdressMobile(CommonFunction.randomstring(10),
                CommonFunction.randomstring(10), CommonFunction.randomstring(10),
                String.valueOf(Math.random()*3));
        app.hmb().createUser(user);
        var group = new GroupData(""
                        , CommonFunction.randomstring(10)
                        , CommonFunction.randomstring(10)
                        , CommonFunction.randomstring(10)).setDeprecated("0000-00-00 00:00:00");

        app.hmb().createGroup(group);
        var group_id = app.getJdbsHelper().getIdByNameGroup(group.getName());
        var user_id = app.getJdbsHelper().getIdByNameUser(user.getFirstname());
        group.withId(String.valueOf(group_id));
        Thread.sleep(3000);
        app.getUserHelper().refreshPage();
        app.getUserHelper().addUserToGroupeUI(user,group);
        Thread.sleep(3000);
        Assertions.assertEquals(true, app.getJdbsHelper().checkUserInGroupe(user_id,group_id));
    }
}

