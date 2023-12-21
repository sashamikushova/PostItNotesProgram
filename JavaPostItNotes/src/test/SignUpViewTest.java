import com.example.SignUpForm;
import com.example.UserRepository;
import com.example.UserService;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.button.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class SignUpViewTest {

    private SignUpForm signUpForm;
    private UserRepository repository;
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setUp() {
        repository = mock(UserRepository.class);
        mongoTemplate = mock(MongoTemplate.class);
        UserService userService = new UserService(repository, mongoTemplate);
        signUpForm = new SignUpForm(userService);
    }

    @Test
    public void signUpWithNonMatchingPasswordsShouldFail() {
        // Arrange
        TextField usernameField = signUpForm.getUsernameField();
        PasswordField passwordField = signUpForm.getPasswordField();
        PasswordField confirmPasswordField = signUpForm.getPasswordConfirmField();
        Span errorMessageField = signUpForm.getErrorMessageField();
        Button submitButton = signUpForm.getSubmitButton();

        // Act
        usernameField.setValue("newuser");
        passwordField.setValue("password");
        confirmPasswordField.setValue("differentpassword");

        submitButton.click();

        // Assert
        assertEquals("Make sure passwords are identical", errorMessageField.getText());
    }

    @Test
    public void signUpWithWrongUsername() {
        // Arrange
        TextField usernameField = signUpForm.getUsernameField();
        PasswordField passwordField = signUpForm.getPasswordField();
        PasswordField confirmPasswordField = signUpForm.getPasswordConfirmField();
        Span errorMessageField = signUpForm.getErrorMessageField();
        Button submitButton = signUpForm.getSubmitButton();

        // Act
        usernameField.setValue("new.user");
        passwordField.setValue("password");
        confirmPasswordField.setValue("password");

        submitButton.click();

        // Assert
        assertEquals("Username cannot contain '.' or '$'", errorMessageField.getText());
    }

    @Test
    public void signUpWithUserUsername() {
        // Arrange
        TextField usernameField = signUpForm.getUsernameField();
        PasswordField passwordField = signUpForm.getPasswordField();
        PasswordField confirmPasswordField = signUpForm.getPasswordConfirmField();
        Span errorMessageField = signUpForm.getErrorMessageField();
        Button submitButton = signUpForm.getSubmitButton();

        // Act
        usernameField.setValue("user");
        passwordField.setValue("password");
        confirmPasswordField.setValue("password");

        submitButton.click();

        // Assert
        assertEquals("Please try another username", errorMessageField.getText());
    }

    @Test
    public void signUpWithoutDB() {
        // Arrange
        TextField usernameField = signUpForm.getUsernameField();
        PasswordField passwordField = signUpForm.getPasswordField();
        PasswordField confirmPasswordField = signUpForm.getPasswordConfirmField();
        Span errorMessageField = signUpForm.getErrorMessageField();
        Button submitButton = signUpForm.getSubmitButton();

        // Act
        usernameField.setValue("newuser");
        passwordField.setValue("password");
        confirmPasswordField.setValue("password");

        submitButton.click();

        // Assert
        assertEquals("Error adding user to the database", errorMessageField.getText());
    }


}
