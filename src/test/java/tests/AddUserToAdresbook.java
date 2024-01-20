package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddUserToAdresbook extends TasteBase {

    @Test
    public void newUserAlmoustInformation() {
        int countGroups = app.getUserHelper().getCountUser();
        app.getUserHelper().openAddNewPage();
        app.getUserHelper().createUserInAdressbook(new UserData("Ivan", "Ivanovich", "Ivanov", "Vana84",
                "putalo", "OOO Boberinvest", "Moscou, Staronaberegnaya 35", "",
                "(800)345-54-56", "hard", "(800)345-54-56", "www.boberbest.com",
                "www.boberbest.ru", "www.boberbest.es", "www.boberbest.ru", "Soloduha"));
        int newCountGroups = app.getUserHelper().getCountUser();
        Assertions.assertEquals(countGroups + 1, newCountGroups);
    }

    @Test
    public void newUserNameAdressMobile() {
        int countGroups = app.getUserHelper().getCountUser();
        app.getUserHelper().openAddNewPage();
        app.getUserHelper().createUserInAdressbook(new UserData().userWithFullNameAdressMobile("Ivan", "Ivanovich",
                "Ivanov", "Moscou, Staronaberegnaya 35", "(800)345-54-56"));
        int newCountGroups = app.getUserHelper().getCountUser();
        Assertions.assertEquals(countGroups + 1, newCountGroups);
    }
}
