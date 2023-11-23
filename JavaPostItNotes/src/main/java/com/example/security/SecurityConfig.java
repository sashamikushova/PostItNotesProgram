package com.example.security;


import com.example.LoginView;
import org.springframework.context.annotation.Configuration;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity{

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        super.configure(http);
        setLoginView(http, LoginView.class);

    }
    @Override
    public void configure(WebSecurity web) throws Exception{

        super.configure(web);
    }

}