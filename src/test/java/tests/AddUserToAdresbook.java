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
//        for (var firstname : List.of("", "first name")) {
//                for (var lastname : List.of("", "last name")) {
//                    for (var address : List.of("", "adres")) {
//                        for (var mobile : List.of("", "mobile")) {
//                            result.add(new UserData().userWithFullNameAdressMobile(firstname,
//                                    lastname, address, mobile));
//                        }
//                    }
//                }
//        }
//        for (int i = 0; i < 2; i++) {
//            result.add(new UserData().userWithFullNameAdressMobile(CommonFunction.randomstring(i * 10),
//                    CommonFunction.randomstring(i * 10), CommonFunction.randomstring(i * 10),
//                    CommonFunction.randomstring(i * 10)));
//        }
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
                CommonFunction.randomstring(10), CommonFunction.randomstring(10), "");
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
                    CommonFunction.randomstring(10), CommonFunction.randomstring( 10),
                    CommonFunction.randomstring( 10))));
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
}

