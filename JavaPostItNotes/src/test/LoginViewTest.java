import com.example.*;
import com.vaadin.flow.component.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginViewTest {

    private LoginView loginView;
    private UserRepository repository;
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setUp() {
        repository = mock(UserRepository.class);
        mongoTemplate = mock(MongoTemplate.class);
        UserService userService = new UserService(repository, mongoTemplate);
        loginView = new LoginView(userService);
    }
    @Test
    void authenticate_ValidUser_SuccessfulLogin() {
        User validUser = new User("abc", "abc");
        Span errorMessageField = loginView.getErrorMessageField();
        TextField usernameField = loginView.getUsername();
        PasswordField passwordField = loginView.getPassword();
        Button loginButton = loginView.getLoginButton();
        usernameField.setValue("abc");
        passwordField.setValue("abc");
        loginButton.click();
        assertEquals("Logged in successfully", errorMessageField.getText());

    }


}
