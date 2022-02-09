import com.example.comics.controller.CustomizeProfileController;
import com.example.comics.model.UserLogin;
import com.example.comics.model.exceptions.FailedProfileCustomizationException;
import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.fagioli.bundle.AccountBundle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCustomizeProfileController {

    @Test
     void testChangeUsername(){
        String newUsername = "giulietto";

        String actual;

        UserLogin.createAccount("giulio","giulio","reader");

        AccountBean accountBean = new AccountBundle();
        accountBean.setUsername(newUsername);
        accountBean.setFirstName(UserLogin.getInstance().getAccount().getFirstName());
        accountBean.setLastName(UserLogin.getInstance().getAccount().getLastName());
        accountBean.setEmail(UserLogin.getInstance().getAccount().getEmail());

        try {
            CustomizeProfileController customizeProfileController = new CustomizeProfileController();
            customizeProfileController.changeUsername(accountBean);
            actual = UserLogin.getInstance().getAccount().getUsername();

        } catch (FailedProfileCustomizationException e) {
            actual = UserLogin.getInstance().getAccount().getUsername();
        }

        String expected = newUsername;

        assertEquals(expected,actual);

    }
}
