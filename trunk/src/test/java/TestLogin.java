import com.example.comics.controller.LoginController;
import com.example.comics.model.exceptions.FailedLoginException;
import com.example.comics.model.fagioli.LoginBean;
import com.example.comics.view1.beans.LoginBean1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLogin {

    @Test
    public void testLoginCorrectCredentials() {
        boolean result;

        LoginController controlLogin = new LoginController();

        LoginBean loginBean = new LoginBean1();
        loginBean.setEmail("giulio");
        loginBean.setPassword("giulio");

        try {
            controlLogin.login(loginBean);
            result = true;
        } catch (FailedLoginException e) {
            result = false;
        }

        assertEquals(true, result);

    }

    @Test
    public void testLoginIncorrectPassword(){

        LoginController controlLogin = new LoginController();

        LoginBean loginBean = new LoginBean1();
        loginBean.setEmail("giulio");
        loginBean.setPassword("password");

        assertThrows(FailedLoginException.class, ()->controlLogin.login(loginBean));
    }

    @Test
    public void testLoginNotRegisteredUsername(){
        boolean result;

        LoginController controlLogin = new LoginController();

        LoginBean loginBean = new LoginBean1();
        loginBean.setEmail("username");
        loginBean.setPassword("giulio");

        try {
            controlLogin.login(loginBean);
            result = false;
        } catch (FailedLoginException e) {
            result = true;
        }

        assertEquals(true, result);
    }

}