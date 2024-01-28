package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AddUserToAdresbook extends TasteBase {


    public static List<UserData> userProvaider() {
        var result = new ArrayList<UserData>();
        for (var firstname : List.of("", "first name")) {
                for (var lastname : List.of("", "last name")) {
                    for (var address : List.of("", "adres")) {
                        for (var mobile : List.of("", "mobile")) {
                            result.add(new UserData().userWithFullNameAdressMobile(firstname,
                                    lastname, address, mobile));
                        }
                    }
                }
        }
        for (int i = 0; i < 2; i++) {
            result.add(new UserData().userWithFullNameAdressMobile(randomstring(i * 10), randomstring(i * 10), randomstring(i * 10),
                    randomstring(i * 10)));
        }
        return result;
    }


    @ParameterizedTest
    @MethodSource("userProvaider")
    public void createMultiplyGroupe(UserData user) throws InterruptedException {
        var oldUsersList = app.getUserHelper().getList();
        app.getUserHelper().createUserInAdressbook(user);
        Thread.sleep(3000);
        var newUsersList = app.getUserHelper().getList();

        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUsersList.sort(compareById);

        var expectedList = new ArrayList<>(oldUsersList);

        expectedList.add(user.withId(newUsersList.get(newUsersList.size()-1).id()).
                withName(user.getFirstname(), user.getLastname()));

        expectedList.sort(compareById);

        Assertions.assertEquals(newUsersList, expectedList);
    }

    @Test
    public void newUserAlmoustInformation() {
        var oldUsersList = app.getUserHelper().getList();
        app.getUserHelper().openAddNewPage();
        var newUser = new UserData("", "Ivan", "Ivanovich", "Ivanov", "Vana84",
                "putalo", "OOO Boberinvest", "Moscou, Staronaberegnaya 35", "",
                "(800)345-54-56", "hard", "(800)345-54-56", "www.boberbest.com",
                "www.boberbest.ru", "www.boberbest.es", "www.boberbest.ru", "Soloduha");
        app.getUserHelper().createUserInAdressbook(newUser);
        var newUsersList = app.getUserHelper().getList();
        oldUsersList.add(newUser.withId(newUsersList.get(newUsersList.size()-1).id()));
        Assertions.assertEquals(newUsersList, oldUsersList);
    }

}

