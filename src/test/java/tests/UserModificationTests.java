package tests;

import common.CommonFunction;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static manager.UserHelper.randomFile;

public class UserModificationTests extends TasteBase {

    @Test
    void canModifayUser() throws InterruptedException {
        if (app.getUserHelper().getCountUser() == 0) {
            app.getUserHelper().createUserInAdressbook(new UserData().withFirstnameLastname(
                    CommonFunction.randomstring(10), CommonFunction.randomstring(10))
                    .withPhoto(randomFile("src/test/resources/images")));
        }
        var oldUsersList = app.getJdbsHelper().getUserList();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsersList.size());
        var modifayUser = new UserData().userWithFullNameAdressMobile(CommonFunction.randomstring(10),
                CommonFunction.randomstring(10),CommonFunction.randomstring(10)
                ,CommonFunction.randomNumber(10) + " " + CommonFunction.randomNumber(10));

        app.getUserHelper().modifyUser(oldUsersList.get(index),modifayUser);
        Thread.sleep(3000);
        var newUsersList = app.getJdbsHelper().getUserList();
        var expectedList = new ArrayList<>(oldUsersList);

        expectedList.set(index,modifayUser.withId(oldUsersList.get(index).id()));

        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUsersList.sort(compareById);
        expectedList.sort(compareById);

        Assertions.assertEquals(newUsersList, expectedList);
    }

    }

