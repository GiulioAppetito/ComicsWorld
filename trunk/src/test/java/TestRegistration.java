import com.example.comics.controller.RegistrationController;
import com.example.comics.model.exceptions.FailedLoginException;
import com.example.comics.model.exceptions.FailedRegistrationException;
import com.example.comics.model.exceptions.MalformedEmailException;
import com.example.comics.model.fagioli.RegistrationBean;
import com.example.comics.view1.beans.RegistrationBean1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRegistration {

    @Test
    public void testRegistrationExistingUsername(){

        RegistrationBean registrationBean = new RegistrationBean1();
        registrationBean.setFirstName("Giulio");
        registrationBean.setLastName("Appetito");
        registrationBean.setEmail("giulio.appetito.ga@gmail.com");
        registrationBean.setUsername("giulio");
        registrationBean.setRole("author");
        registrationBean.setPassword("giulio");

        RegistrationController registrationController = new RegistrationController();

        assertThrows(FailedRegistrationException.class,()->registrationController.registerNewAccount(registrationBean));
    }

    @Test
    public void testRegistrationMalformedEmail(){

        RegistrationBean registrationBean = new RegistrationBean1();
        registrationBean.setFirstName("Giulio");
        registrationBean.setLastName("Appetito");
        registrationBean.setEmail("giulio.appetito.gagmail.com");
        registrationBean.setUsername("giuliauswdbiwbdfssdaafs");
        registrationBean.setRole("author");
        registrationBean.setPassword("giulio");

        RegistrationController registrationController = new RegistrationController();

        assertThrows(MalformedEmailException.class,()->registrationController.registerNewAccount(registrationBean));
    }

    @Test
    public void testRegistrationValidCredentials(){
        boolean isRegistrationSuccessed;

        RegistrationBean registrationBean = new RegistrationBean1();
        registrationBean.setFirstName("Giulio");
        registrationBean.setLastName("Appetito");
        registrationBean.setEmail("giulio.appetito.ga@gmail.com");
        registrationBean.setUsername("giuliauswdbiwbdsdsfssdaafs");
        registrationBean.setRole("author");
        registrationBean.setPassword("giulio");

        try{
            RegistrationController registrationController = new RegistrationController();
            registrationController.registerNewAccount(registrationBean);
            isRegistrationSuccessed = true;
        } catch (FailedRegistrationException e) {
            isRegistrationSuccessed = false;
        } catch (MalformedEmailException e) {
            isRegistrationSuccessed = false;
        }

        assertEquals(true,isRegistrationSuccessed);
    }
}
