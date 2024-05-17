package tests;

import common.CommonFunction;
import model.GroupData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static manager.UserHelper.randomFile;

public class UserRemuvalTest extends TasteBase {

    @Test
    public void testDelete() throws InterruptedException {
        if (app.hmb().getUserCount() == 0) {
            app.getUserHelper().openAddNewPage();
            app.getUserHelper().createUserInAdressbook(new UserData().withFirstnameLastname(
                    CommonFunction.randomstring(10), CommonFunction.randomstring(10)));
        }

        var oldUserList = app.getJdbsHelper().getUserList();
        var rnd = new Random();
        var index = rnd.nextInt(oldUserList.size());
        app.getUserHelper().removeUser(oldUserList.get(index));
        var newUserList = app.getJdbsHelper().getUserList();
        var expectedList = new ArrayList<>(oldUserList);
        expectedList.remove(index);
        Assertions.assertEquals(newUserList, expectedList);
    }

    @Test
    public void testDeleteAllUsers() throws InterruptedException {
        if (app.hmb().getUserCount() == 0) {
            app.getUserHelper().openAddNewPage();
            app.getUserHelper().createUserInAdressbook(new UserData().withFirstnameLastname(
                    CommonFunction.randomstring(10), CommonFunction.randomstring(10))
                    .withPhoto(randomFile("src/test/resources/images")));
        }
        app.getUserHelper().removeAllUser();
        var newUserList = app.getJdbsHelper().getUserList();
        Thread.sleep(1000);
        Assertions.assertEquals(0, newUserList);
    }

    @Test
    public void testDeleteSQL() throws InterruptedException {
        if (app.hmb().getUserCount() == 0) {
            app.hmb().createUser(new UserData().UserDataFestLastMidlMob("",
                    CommonFunction.randomstring(10),
                    CommonFunction.randomstring(10),
                    CommonFunction.randomstring(10),
                    CommonFunction.randomstring(10)));
                    Thread.sleep(1000);
        }

        var oldUserList = app.hmb().getUserList();
        var rnd = new Random();
        var index = rnd.nextInt(oldUserList.size());
        app.getUserHelper().removeUser(oldUserList.get(index));
        var newUserList = app.hmb().getUserList();
        var expectedList = new ArrayList<>(oldUserList);
        expectedList.remove(index);
        Assertions.assertEquals(newUserList, expectedList);
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
    public void deleteUserFromGroup(UserData user) throws InterruptedException {
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
        app.getUserHelper().deleteUserInGroupeUI(user,group);
        Assertions.assertEquals(false, app.getJdbsHelper().checkUserInGroupe(user_id,group_id));
    }
}
