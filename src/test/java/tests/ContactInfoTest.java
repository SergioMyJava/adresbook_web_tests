package tests;

import common.CommonFunction;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTest extends TasteBase {

    @Test
    void testPhone() {
        var users = app.hmb().getUserListT();
//        if (app.hmb().getUserCount() == 0) {
//            app.hmb().createUser(new UserData().UserDataFestLastMidlMob("",
//                    CommonFunction.randomstring(10),
//                    CommonFunction.randomstring(10),
//                    CommonFunction.randomstring(10),
//                    CommonFunction.randomstring(10)));
//        }

        var user = users.get(0);
        var phones = app.getUserHelper().getPhone(user);

        var expected = Stream.of(user.getHome(), user.getMobile(), user.getWork(), user.getSecondary())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }
}
