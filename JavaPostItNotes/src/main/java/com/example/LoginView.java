package com.example;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;

@Route("login")
@PageTitle("Login Post It Notes")

public class LoginView extends VerticalLayout {
    private final UserService userService;
    private H3 title;

    private TextField username;

    private PasswordField password;
    private Span errorMessageField;
    private Button loginButton;

    public LoginView(UserService userService) {
        RouterLink routerLink = new RouterLink("Sign Up", SignUpView.class);
        this.userService = userService;
        title = new H3("Log In");
        username = new TextField("Username");
        password = new PasswordField("Password");

        errorMessageField = new Span();

        loginButton = new Button("Log In",
                event -> authenticate(username.getValue(),
                        password.getValue()));
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(title, username, password,
                errorMessageField,
                loginButton);
        setHorizontalComponentAlignment(Alignment.CENTER);
        add(routerLink);
    }
    private void authenticate(String username, String password) {
        User foundUser = userService.findUserByUsername(username);
        if (foundUser != null && foundUser.comparePassword(password)) {
            VaadinSession.getCurrent().setAttribute(User.class, foundUser);
            String dynamicCollectionName = username;
            CustomMongoConfig.setDynamicCollectionName(dynamicCollectionName);
            UI.getCurrent().navigate("tasks");
            Notification.show("Logged in successfully");
        } else {
            Notification.show("Invalid username or password");
        }
    }

}
