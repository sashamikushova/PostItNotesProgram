package com.example;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

import javax.management.Notification;
import java.util.stream.Stream;

public class SignUpForm extends FormLayout {

    private H3 title;

    private TextField username;

    private PasswordField password;
    private PasswordField passwordConfirm;
    private Span errorMessageField;
    private Button submitButton;
    private final UserService userService;

    public SignUpForm(UserService userService) {
        this.userService = userService;
        title = new H3("Sign Up");
        username = new TextField("Username");
        password = new PasswordField("Password");
        passwordConfirm = new PasswordField("Confirm password");

        setRequiredIndicatorVisible(username, password, passwordConfirm);

        errorMessageField = new Span();

        submitButton = new Button("Join Post It Notes",
                event -> signup(username.getValue(),
                        password.getValue(),
                        passwordConfirm.getValue()

                ));
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(title, username, password,
                passwordConfirm, errorMessageField,
                submitButton);

        // Max width of the Form
        setMaxWidth("500px");
        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP));


    }

    private void signup(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            errorMessageField.setText("Make sure passwords are identical");
        } else {
            User user = new User(username, password);
            if(userService.findUserByUsername(username) == null){
                User addedUser = userService.add(user);
                if (addedUser != null) {
                    errorMessageField.setText("You successfully joined Post It Notes");
                } else {
                    errorMessageField.setText("Error adding user to the database");
                }
            }
            else{
                errorMessageField.setText("Please try another username");
            }
        }

    }

    public PasswordField getPasswordField() { return password; }

    public PasswordField getPasswordConfirmField() { return passwordConfirm; }

    public Span getErrorMessageField() { return errorMessageField; }

    public Button getSubmitButton() { return submitButton; }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }


}
