package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class UserRemuvalTest extends TasteBase {

    @Test
    public void testDelete() throws InterruptedException {
        if (app.getUserHelper().getCountUser() == 0) {
            app.getUserHelper().openAddNewPage();
            app.getUserHelper().createUserInAdressbook(new UserData("", "Ivan", "Ivanovich", "Ivanov", "Vana84",
                    "putalo", "OOO Boberinvest", "Moscou, Staronaberegnaya 35", "",
                    "(800)345-54-56", "hard", "(800)345-54-56", "www.boberbest.com",
                    "www.boberbest.ru", "www.boberbest.es", "www.boberbest.ru", "Soloduha"));
        }

        var oldUserList = app.getUserHelper().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldUserList.size());
        app.getUserHelper().removeUser(oldUserList.get(index));
  //      Thread.sleep(1000);
        var newUserList = app.getUserHelper().getList();
        var expectedList = new ArrayList<>(oldUserList);
        expectedList.remove(index);
        Assertions.assertEquals(newUserList, expectedList);
    }

    @Test
    public void testDeleteAllUsers() throws InterruptedException {
        if (app.getUserHelper().getCountUser() == 0) {
            app.getUserHelper().openAddNewPage();
            app.getUserHelper().createUserInAdressbook(new UserData("", "Ivan", "Ivanovich", "Ivanov", "Vana84",
                    "putalo", "OOO Boberinvest", "Moscou, Staronaberegnaya 35", "",
                    "(800)345-54-56", "hard", "(800)345-54-56", "www.boberbest.com",
                    "www.boberbest.ru", "www.boberbest.es", "www.boberbest.ru", "Soloduha"));
        }
        app.getUserHelper().removeAllUser();
        var newUserList = app.getUserHelper().getList();
        Thread.sleep(1000);
        Assertions.assertEquals(0,newUserList);
    }
}
