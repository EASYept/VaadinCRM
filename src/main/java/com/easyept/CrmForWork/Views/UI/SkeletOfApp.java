package com.easyept.CrmForWork.Views.UI;

import com.easyept.CrmForWork.Views.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
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
        H1 logo = new H1("CRM for Work");
        logo.setClassName("logo");
        logo.setMaxHeight("20em");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.setClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setMaxHeight("5em");

        addToNavbar(header);

    }



}
