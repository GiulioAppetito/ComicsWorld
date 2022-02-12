package junit;

import com.example.comics.controller.LoginController;
import com.example.comics.model.exceptions.FailedLoginException;
import com.example.comics.model.fagioli.LoginBean;
import com.example.comics.view1.beans.LoginBean1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** @author Anastasia Brinati
 *          Matricola 0266530
 */

class TestLoginController {

    private static final String GIULIO = "giulio";
    private static final String CORRECT_PASSWORD = "giulio";

    @Test
    void testLoginCorrectCredentials() {
        boolean result;

        LoginController controlLogin = new LoginController();

        LoginBean loginBean = new LoginBean1();
        loginBean.setEmail(GIULIO);
        loginBean.setPassword(CORRECT_PASSWORD);

        try {
            controlLogin.login(loginBean);
            result = true;
        } catch (FailedLoginException e) {
            result = false;
        }

        assertEquals(true, result);

    }

    @Test
    void testLoginIncorrectPassword(){

        LoginController controlLogin = new LoginController();
        final String username = GIULIO;
        final String wrongPassword = "wrong";

        LoginBean loginBean = new LoginBean1();
        loginBean.setEmail(username);
        loginBean.setPassword(wrongPassword);

        assertThrows(FailedLoginException.class, ()->controlLogin.login(loginBean));
    }

    @Test
    void testLoginNotRegisteredUsername(){
        boolean result;

        final String notRegisteredUsername = "notRegistered";

        LoginController controlLogin = new LoginController();

        LoginBean loginBean = new LoginBean1();
        loginBean.setEmail(notRegisteredUsername);
        loginBean.setPassword("password");

        try {
            controlLogin.login(loginBean);
            result = false;
        } catch (FailedLoginException e) {
            result = true;
        }

        assertEquals(true, result);
    }

}