package com.easyept.CrmForWork.Views;

import com.easyept.CrmForWork.Views.UI.SkeletOfApp;
import com.easyept.CrmForWork.entity.BusinessTrip;
import com.easyept.CrmForWork.entity.Factory;
import com.easyept.CrmForWork.entity.Person;
import com.easyept.CrmForWork.service.BusinessTripService;
import com.easyept.CrmForWork.service.FactoryService;
import com.easyept.CrmForWork.service.PersonService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//@Route(value = "/createTrip", layout = SkeletOfApp.class)
public class BusinessTripsCreateForm extends VerticalLayout { // TODO add validation to fields

    private PersonService personService;
    private FactoryService factoryService;
    private BusinessTripService businessTripService;



    public BusinessTripsCreateForm(@Autowired PersonService personService,
                                   @Autowired FactoryService factoryService,
                                   @Autowired BusinessTripService businessTripService) {
        this.factoryService = factoryService;
        this.personService = personService;
        this.businessTripService = businessTripService;

        add(new H1("CREATE BUSINESS TRIP"));
        add(tripForm());
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

    }



    private VerticalLayout tripForm() {
        VerticalLayout layout = new VerticalLayout();

        List<Person> personsForSettingToBusinessTrip = new ArrayList<>();

        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setPadding(true);

        //combo boxes
        ComboBox<Factory> dropDownFactories = new ComboBox<>("Factories");
        dropDownFactories.setItemLabelGenerator(Factory::getFactoryName);
        dropDownFactories.setItems(factoryService.findAll());

        //adding comboBoxes to layout
        layout.add(personsComboBoxes());
        layout.add(dropDownFactories);

        //date fields create+add
        DatePicker startDate = new DatePicker("Starting Date");
        DatePicker endDate = new DatePicker("End Date");
        layout.add(startDate);
        layout.add(endDate);

        //buttons
        Button submitMapAllFieldsToTripAndSave = new Button("Submit", event -> {
            BusinessTrip trip = new BusinessTrip();
            trip.setFactory(dropDownFactories.getValue());
            trip.setDateOfTrip(Date.valueOf(startDate.getValue()));

            //collecting all persons to save
            //collecting all items from main layout
            List<Component> collectedElements = layout.getChildren().collect(Collectors.toList());
            //find FormLayout
            for (Component comp : collectedElements) { //TODO make it with binder
                if (comp.getClass() == FlexLayout.class) {
                        //from all ComboBoxes in FormLayout saving Persons to list
                        List<Component> componentList = comp.getChildren().collect(Collectors.toList());
                        for (Component c : componentList) {
                            if (c instanceof ComboBox) {
                                Object valueOfBox = ((ComboBox) c).getValue();
                                personsForSettingToBusinessTrip.add((Person) valueOfBox);
                            }
                        }
                    }
            }

            //set persons
            trip.setPersons(
                    personsForSettingToBusinessTrip.stream()
                                                .distinct()
                                                .collect(Collectors.toList())); // Deleting duplicates if presented

            // saving entity
            businessTripService.add(trip);

            //redirect to main page
            UI.getCurrent().navigate(BusinessTripView.class);

        });

        submitMapAllFieldsToTripAndSave.setThemeName("primary");
        layout.add(submitMapAllFieldsToTripAndSave);


        return layout;

    }

    private FlexLayout personsComboBoxes() {
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setClassName("personsComboBoxes");
        flexLayout.setMaxWidth("90%");
        flexLayout.setAlignItems(Alignment.END);

        ComboBox<Person> dropDownPersons = new ComboBox<>("Person");
        dropDownPersons.setItemLabelGenerator(Person::getSecondName);
        List<Person> allPersons = personService.findAll();
        dropDownPersons.setItems(allPersons);

        Button buttonAddComboBoxOfPersons = new Button("Add another person", event -> {
            ComboBox<Person> dropDownPersons2 = new ComboBox<>("Additional Person");
            dropDownPersons2.setItems(allPersons);
            dropDownPersons2.setItemLabelGenerator(Person::getSecondName);
            flexLayout.add(dropDownPersons2);
        });
        buttonAddComboBoxOfPersons.setWidthFull();

        flexLayout.add(buttonAddComboBoxOfPersons);
        flexLayout.add(dropDownPersons);
        flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        flexLayout.setAlignContent(FlexLayout.ContentAlignment.CENTER);
        flexLayout.setJustifyContentMode(JustifyContentMode.AROUND);
        flexLayout.setWidthFull();
        flexLayout.setClassName("PersonsComboBoxes");

        return flexLayout;
    }




}
