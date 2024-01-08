import model.UserData;
import org.junit.jupiter.api.Test;

public class AddUserToAdresbook extends TestBase {

    @Test
    public void newUserAlmoustInformation() {
        openAddNewPage();
        createUserInAdressbook(new UserData("Ivan", "Ivanovich", "Ivanov", "Vana84",
                "putalo", "OOO Boberinvest", "Moscou, Staronaberegnaya 35", "",
                "(800)345-54-56", "hard", "(800)345-54-56", "www.boberbest.com",
                "www.boberbest.ru", "www.boberbest.es", "www.boberbest.ru", "Soloduha"));
    }

    @Test
    public void newUserNameAdressMobile() {
        openAddNewPage();
        createUserInAdressbook(new UserData().userWithFullNameAdressMobile("Ivan", "Ivanovich",
                "Ivanov", "Moscou, Staronaberegnaya 35", "(800)345-54-56"));
    }
}
