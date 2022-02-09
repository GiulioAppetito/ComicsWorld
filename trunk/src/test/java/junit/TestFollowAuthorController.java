package junit;

import com.example.comics.controller.FollowAuthorController;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.bundle.AuthorBundle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestFollowAuthorController {

    @Test
    void testFollowAuthorWithFollowedAuthor(){
        UserLogin.createAccount("giulio","giulio","reader");

        AuthorBean authorBean = new AuthorBundle();
        authorBean.setUsername("stanlee");
        authorBean.setFirstName("Stan");
        authorBean.setLastName("Lee");

        FollowAuthorController followAuthorController = new FollowAuthorController();
        boolean actual = followAuthorController.isAuthorFollowed(authorBean);

        assertEquals(true,actual);
    }

}
