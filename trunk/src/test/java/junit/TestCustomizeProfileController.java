package junit;

import com.example.comics.controller.CustomizeProfileController;
import com.example.comics.model.UserLogin;
import com.example.comics.model.exceptions.FailedProfileCustomizationException;
import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.fagioli.bundle.AccountBundle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** @author Anastasia Brinati & Giulio Appetito
 *          Matricola 0266530
 */

class TestCustomizeProfileController {

    @Test
     void testChangeUsername(){
        String newUsername = "username";
        String actualUsername = "giulio";
        String changedUsername;

        UserLogin.createAccount(actualUsername,"giulio","reader");

        AccountBean accountBean = new AccountBundle();
        accountBean.setUsername(newUsername);
        accountBean.setFirstName(UserLogin.getInstance().getAccount().getFirstName());
        accountBean.setLastName(UserLogin.getInstance().getAccount().getLastName());
        accountBean.setEmail(UserLogin.getInstance().getAccount().getEmail());

        try {
            CustomizeProfileController customizeProfileController = new CustomizeProfileController();
            customizeProfileController.changeUsername(accountBean);
            changedUsername = UserLogin.getInstance().getAccount().getUsername();

            //reset del cambiamento
            accountBean.setUsername(actualUsername);
            customizeProfileController.changeUsername(accountBean);

        } catch (FailedProfileCustomizationException e) {
            changedUsername = UserLogin.getInstance().getAccount().getUsername();
        }

        String expected = newUsername;
        assertEquals(expected,changedUsername);

    }
}
