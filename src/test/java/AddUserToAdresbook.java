import manager.ApplicationManager;
import manager.UserHelper;
import org.junit.jupiter.api.Test;

public class AddUserToAdresbook extends ApplicationManager {

    @Test
    public void newUserAlmoustInformation() {
        openAddNewPage();
        createUserInAdressbook(new UserHelper("Ivan", "Ivanovich", "Ivanov", "Vana84",
                "putalo", "OOO Boberinvest", "Moscou, Staronaberegnaya 35", "",
                "(800)345-54-56", "hard", "(800)345-54-56", "www.boberbest.com",
                "www.boberbest.ru", "www.boberbest.es", "www.boberbest.ru", "Soloduha"));
    }

    @Test
    public void newUserNameAdressMobile() {
        openAddNewPage();
        createUserInAdressbook(new UserHelper().userWithFullNameAdressMobile("Ivan", "Ivanovich",
                "Ivanov", "Moscou, Staronaberegnaya 35", "(800)345-54-56"));
    }
}
