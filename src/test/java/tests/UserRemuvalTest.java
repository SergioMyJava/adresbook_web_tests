package tests;

import common.CommonFunction;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static manager.UserHelper.randomFile;

public class UserRemuvalTest extends TasteBase {

    @Test
    public void testDelete() throws InterruptedException {
        if (app.getJdbsHelper().getUserList().size() == 0) {
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
        if (app.getJdbsHelper().getUserList().size() == 0) {
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
}
