package tests;

import model.UserData;
import org.junit.jupiter.api.Test;

public class UserModificationTests extends TasteBase {

    @Test
    void canModifayGroup() {
        if (!app.getUserHelper().isUserPresent()) {
            app.getUserHelper().createUserInAdressbook(new UserData("", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", ""));
        }
        app.getUserHelper().modifyUser(new UserData().userWithFullNameAdressMobile("ModifyName", "ModifyMiddleName",
                "ModifyLastname", "Modify addres", "Modify mobile (800)345-54-56"));
    }
}
