package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class AddUserToAdresbook extends TasteBase {


    public static List<UserData> userProvaider() {
        var result = new ArrayList<UserData>();
        for (var firstname : List.of("", "first name")) {
            for (var middlename : List.of("", "middle name")) {
                for (var lastname : List.of("", "last name")) {
                    for (var address : List.of("", "adres")) {
                        for (var mobile : List.of("", "mobile")) {
                            result.add(new UserData().userWithFullNameAdressMobile(firstname, middlename,
                                    lastname, address, mobile));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new UserData().userWithFullNameAdressMobile(randomstring(i * 10),
                    randomstring(i * 10), randomstring(i * 10), randomstring(i * 10),
                    randomstring(i * 10)));
        }
        return result;
    }


    @ParameterizedTest
    @MethodSource("userProvaider")
    public void createMultiplyGroupe(UserData user) {
        int countUser = app.getUserHelper().getCountUser();
        app.getUserHelper().createUserInAdressbook(user);
        int newCountUser = app.getUserHelper().getCountUser();
        Assertions.assertEquals(countUser + 1, newCountUser);

    }

    @Test
    public void newUserAlmoustInformation() {
        int countGroups = app.getUserHelper().getCountUser();
        app.getUserHelper().openAddNewPage();
        app.getUserHelper().createUserInAdressbook(new UserData("", "Ivan", "Ivanovich", "Ivanov", "Vana84",
                "putalo", "OOO Boberinvest", "Moscou, Staronaberegnaya 35", "",
                "(800)345-54-56", "hard", "(800)345-54-56", "www.boberbest.com",
                "www.boberbest.ru", "www.boberbest.es", "www.boberbest.ru", "Soloduha"));
        int newCountGroups = app.getUserHelper().getCountUser();
        Assertions.assertEquals(countGroups + 1, newCountGroups);
    }

//    @Test
//    public void newUserNameAdressMobile() {
//        int countGroups = app.getUserHelper().getCountUser();
//        app.getUserHelper().openAddNewPage();
//        app.getUserHelper().createUserInAdressbook(new UserData().userWithFullNameAdressMobile("Ivan", "Ivanovich",
//                "Ivanov", "Moscou, Staronaberegnaya 35", "(800)345-54-56"));
//        int newCountGroups = app.getUserHelper().getCountUser();
//        Assertions.assertEquals(countGroups + 1, newCountGroups);
//    }
//
//    @Test
//    public void newUserMultiply() throws InterruptedException {
//        int n = 5;
//        int countGroups = app.getUserHelper().getCountUser();
//        for (int k = 0; k < n; k++) {
//            app.getUserHelper().openAddNewPage();
//            app.getUserHelper().createUserInAdressbook(new UserData().userWithFullNameAdressMobile(randomstring(k), "Ivanovich",
//                    "Ivanov", "Moscou, Staronaberegnaya 35", "(800)345-54-56"));
//        }
//        int newCountGroups = app.getUserHelper().getCountUser();
//        Thread.sleep(1000);
//        Assertions.assertEquals(countGroups + n, newCountGroups);
//    }
}

