package com.easyept.CrmForWork.Views.UI;

import com.easyept.CrmForWork.Views.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;


public class SkeletOfApp extends AppLayout {

    public SkeletOfApp() {
        createHeader();
        createDrawer();


        
    }

    private void createDrawer() {
        RouterLink persons = new RouterLink("Persons", PersonView.class);
        RouterLink factories = new RouterLink("Factories", FactoryView.class);
        RouterLink business_trips = new RouterLink("Business Trips", BusinessTripView.class);
        RouterLink statistics = new RouterLink("Stats", StatisticsPage.class);
        persons.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(
                new VerticalLayout(
                        persons,
                        factories,
                        business_trips,
                        statistics
                )
        );
    }

    private void createHeader() {
        H3 logo = new H3("CRM for Work");
        logo.setClassName("logo");
        logo.setMaxHeight("10em");
        Anchor logout = new Anchor("/logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.setClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);

        addToNavbar(header);

    }



}
