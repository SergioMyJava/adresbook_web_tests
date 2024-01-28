package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class UserModificationTests extends TasteBase {

    @Test
    void canModifayUser() throws InterruptedException {
        if (app.getUserHelper().getCountUser() == 0) {
            app.getUserHelper().createUserInAdressbook(new UserData("", "",
                    "", "", "", "", "", "",
                    "", "", "","", "", "", "",
                    "", ""));
        }
        var oldUsersList = app.getUserHelper().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsersList.size());
        var modifayUser = new UserData().userWithFullNameAdressMobile("Lavon",
                "Volsky","s.Baukov","+900-345-567");
        app.getUserHelper().modifyUser(oldUsersList.get(index),modifayUser);
        Thread.sleep(3000);
        var newUsersList = app.getUserHelper().getList();
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

