package com.easyept.CrmForWork.security;

import com.easyept.CrmForWork.Views.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

@Component //Allows adding the navigation listener globally to all UI instances by using a service init listener. Spring takes care of registering it
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener { //

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::beforeEnter); // Adds the before enter listener
        });
    }

    /**
     * Reroutes the user if (s)he is not authorized to access the view.
     *
     * @param event
     *            before navigation event with event details
     */
    private void beforeEnter(BeforeEnterEvent event) {
        if (!LoginView.class.equals(event.getNavigationTarget()) // Ignores the login view itself
                && !SecurityUtils.isUserLoggedIn()) { //Only redirects if user is not logged in. See below.
            event.rerouteTo(LoginView.class); //Actual rerouting the login view if needed.
        }
    }
}
