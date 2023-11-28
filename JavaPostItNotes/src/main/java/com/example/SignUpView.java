package com.example;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Sign Up Form")
@Route("sign_up")
@AnonymousAllowed
public class SignUpView extends VerticalLayout {

    public SignUpView(UserService userService) {
        SignUpForm registrationForm = new SignUpForm(userService);
        setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);
        add(registrationForm);


    }
}