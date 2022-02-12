package junit;

import com.example.comics.controller.RegistrationController;
import com.example.comics.model.exceptions.FailedRegistrationException;
import com.example.comics.model.exceptions.MalformedEmailException;
import com.example.comics.model.fagioli.RegistrationBean;
import com.example.comics.view1.beans.RegistrationBean1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** @author Giulio Appetito
 *          Matricola 0273291
 */

class TestRegistrationController {

    private static final String AUTHOR = "author";
    private static final String GIULIO = "giulio";
    private static final String APPETITO = "Appetito";
    private static final String GIULIOUP = "Giulio";

    @Test
    void testRegistrationExistingUsername(){

        RegistrationBean registrationBean = new RegistrationBean1();
        registrationBean.setFirstName(GIULIOUP);
        registrationBean.setLastName(APPETITO);
        registrationBean.setEmail("giulio.appetito.ga@gmail.com");
        registrationBean.setUsername(GIULIO);
        registrationBean.setRole(AUTHOR);
        registrationBean.setPassword(GIULIO);

        RegistrationController registrationController = new RegistrationController();

        assertThrows(FailedRegistrationException.class,()->registrationController.registerNewAccount(registrationBean));
    }

    @Test
    void testRegistrationMalformedEmail(){

        RegistrationBean registrationBean = new RegistrationBean1();
        registrationBean.setFirstName(GIULIOUP);
        registrationBean.setLastName(APPETITO);
        registrationBean.setEmail("giulio.appetito.gagmail.com");
        registrationBean.setUsername("usernameRandom");
        registrationBean.setRole(AUTHOR);
        registrationBean.setPassword(GIULIO);

        RegistrationController registrationController = new RegistrationController();

        assertThrows(MalformedEmailException.class,()->registrationController.registerNewAccount(registrationBean));
    }

    @Test
    void testRegistrationValidCredentials(){
        boolean isRegistrationSuccessed;

        RegistrationBean registrationBean = new RegistrationBean1();
        registrationBean.setFirstName(GIULIOUP);
        registrationBean.setLastName(APPETITO);
        registrationBean.setEmail("giulio.appetito.ga@gmail.com");
        registrationBean.setUsername("casualUsername");
        registrationBean.setRole(AUTHOR);
        registrationBean.setPassword(GIULIO);

        try{
            RegistrationController registrationController = new RegistrationController();
            registrationController.registerNewAccount(registrationBean);
            isRegistrationSuccessed = true;
        } catch (FailedRegistrationException | MalformedEmailException e) {
            isRegistrationSuccessed = false;
        }

        assertEquals(true,isRegistrationSuccessed);
    }
}
