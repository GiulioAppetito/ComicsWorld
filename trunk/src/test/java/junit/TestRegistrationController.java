package junit;

import com.example.comics.controller.RegistrationController;
import com.example.comics.model.exceptions.FailedRegistrationException;
import com.example.comics.model.exceptions.MalformedEmailException;
import com.example.comics.model.fagioli.RegistrationBean;
import com.example.comics.view1.beans.RegistrationBean1;
import com.sun.mail.iap.ByteArray;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Random;

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
        String randomUsername = getRandomString(10);

        RegistrationBean registrationBean = new RegistrationBean1();
        registrationBean.setFirstName(GIULIOUP);
        registrationBean.setLastName(APPETITO);
        registrationBean.setEmail("giulio.appetito.ga@gmail.com");
        registrationBean.setUsername(randomUsername);
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

    static String getRandomString(int i) {
        byte[] bytearray = new byte[256];
        String mystring;
        StringBuffer thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray);

        mystring
                = new String(bytearray, Charset.forName("UTF-8"));

        thebuffer = new StringBuffer();

        //remove all spacial char
        theAlphaNumericS
                = mystring
                .replaceAll("[^A-Z0-9]", "");

        //random selection
        for (int m = 0; m < theAlphaNumericS.length(); m++) {

            if (Character.isLetter(theAlphaNumericS.charAt(m))
                    && (i > 0)
                    || Character.isDigit(theAlphaNumericS.charAt(m))
                    && (i > 0)) {

                thebuffer.append(theAlphaNumericS.charAt(m));
                i--;
            }
        }

        // the resulting string
        return thebuffer.toString();
    }
}
