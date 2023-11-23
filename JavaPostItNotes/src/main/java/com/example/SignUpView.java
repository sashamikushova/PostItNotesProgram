package com.example;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Sign Up Form")
@Route("sign_up")
public class SignUpView extends VerticalLayout {

    public SignUpView() {
        SignUpForm registrationForm = new SignUpForm();
        setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);
        add(registrationForm);

    }
}