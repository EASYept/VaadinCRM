package com.easyept.CrmForWork.Views;


import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@Route("/login")
public class LoginView extends LoginForm implements BeforeEnterObserver { //Allows receiving navigation events before the view is rendered

    private static final LoginOverlay login = new LoginOverlay(); //

    public LoginView(){
        setAction("login");
//        login.setAction("login"); //
//        login.setOpened(true); //
//        login.setTitle("Spring Secured Vaadin");
//        login.setDescription("Login Overlay Example");
//        add(login);
//        getElement().appendChild(login.getElement()); //
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) { // BeforeEnterEvent gives us access to query parameters
        // inform the user about an authentication error
        // (yes, the API for resolving query parameters is annoying...)
        if(!event.getLocation().getQueryParameters().getParameters().getOrDefault("error", Collections.emptyList()).isEmpty()) {
            login.setError(true); //Shows the default error message the login dialog provides out of the box
        }
    }


}
