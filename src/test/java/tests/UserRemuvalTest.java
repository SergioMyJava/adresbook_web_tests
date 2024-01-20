package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRemuvalTest extends TasteBase {

    @Test
    public void testDelete() throws InterruptedException {
        if (!app.getUserHelper().isUserPresent()) {
            app.getUserHelper().openAddNewPage();
            app.getUserHelper().createUserInAdressbook(new UserData("Ivan", "Ivanovich", "Ivanov", "Vana84",
                    "putalo", "OOO Boberinvest", "Moscou, Staronaberegnaya 35", "",
                    "(800)345-54-56", "hard", "(800)345-54-56", "www.boberbest.com",
                    "www.boberbest.ru", "www.boberbest.es", "www.boberbest.ru", "Soloduha"));
        }
        int countGroups = app.getUserHelper().getCountUser();
        app.getUserHelper().removeUser();
        int newCountGroups = app.getUserHelper().getCountUser();
        Assertions.assertEquals(countGroups-1,newCountGroups);
    }
}
