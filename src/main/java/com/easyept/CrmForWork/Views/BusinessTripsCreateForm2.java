package com.easyept.CrmForWork.Views;

import com.easyept.CrmForWork.Views.UI.SkeletOfApp;
import com.easyept.CrmForWork.entity.BusinessTrip;
import com.easyept.CrmForWork.entity.Factory;
import com.easyept.CrmForWork.entity.Person;
import com.easyept.CrmForWork.service.BusinessTripService;
import com.easyept.CrmForWork.service.FactoryService;
import com.easyept.CrmForWork.service.PersonService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Route(value = "/createTrip", layout = SkeletOfApp.class)
public class BusinessTripsCreateForm2 extends VerticalLayout {
    // TODO add validation to fields
    // TODO on click this page making 4 SQL queries (2 times in business_trip table) find out why? (https://www.youtube.com/watch?v=_7aaqE1J43o)

    private PersonService personService;
    private FactoryService factoryService;
    private BusinessTripService businessTripService;
    private List<Person> allPersons;


    ComboBox<Person> dropDownPersons = new ComboBox<>("Person");

    public BusinessTripsCreateForm2(@Autowired PersonService personService,
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

        allPersons = personService.findAll(); // TODO bad idea to finding all HOW TO REDUCE AMOUNT?
        BusinessTrip trip = new BusinessTrip();
        VerticalLayout layout = new VerticalLayout();

        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setPadding(true);

        //create and add comboBoxes to layout
        ComboBox<Factory> dropDownFactories = getFactoryComboBox();
        FlexLayout personsComboBoxes = getPersonsComboBoxes();
        layout.add(personsComboBoxes);
        layout.add(dropDownFactories);

        //date fields create+add
        DatePicker startDate = new DatePicker("Starting Date");
        DatePicker endDate = new DatePicker("End Date");
        layout.add(startDate, endDate);

        List<Person> personsToMapToBusinessTrip = new ArrayList<>();
        //button
        Button submitMapAllFieldsToTripAndSave = new Button("Submit", event -> {
            trip.setFactory(dropDownFactories.getValue());
            trip.setDateOfTrip(Date.valueOf(startDate.getValue()));
            trip.setEndOfTrip(Date.valueOf(endDate.getValue()));

            List<Component> collectAllPersons = personsComboBoxes.getChildren().collect(Collectors.toList());
            for (Component person : collectAllPersons) {
                if (person instanceof ComboBox) {
                    Object valueOfBox = ((ComboBox) person).getValue();
                    personsToMapToBusinessTrip.add((Person) valueOfBox);
                }
            }

            //set persons
            trip.setPersons(personsToMapToBusinessTrip.stream()
                                                .distinct()
                                                .collect(Collectors.toList())); // Deleting duplicates if presented

            // saving entity
            businessTripService.add(trip);

            //redirect to BusinessTripView
            UI.getCurrent().navigate(BusinessTripView.class);
        });

        submitMapAllFieldsToTripAndSave.setThemeName("primary");
        layout.add(submitMapAllFieldsToTripAndSave);
        return layout;
    }

    private ComboBox<Factory> getFactoryComboBox() {
        ComboBox<Factory> dropDownFactories = new ComboBox<>("Factories");
        dropDownFactories.setItemLabelGenerator(Factory::getFactoryName);
        dropDownFactories.setItems(factoryService.findAll());
        return dropDownFactories;
    }

    private FlexLayout getPersonsComboBoxes() {
        FlexLayout flexLayout = new FlexLayout();
        Button buttonAddComboBoxOfPersons = new Button("Add another person");
        buttonAddComboBoxOfPersons.setWidthFull();

        //add click action
        addClickAction_AddAnotherPerson(buttonAddComboBoxOfPersons, allPersons, flexLayout);
        //settings for comboBox
        dropDownPersonsSettings();

        flexLayout.add(buttonAddComboBoxOfPersons, dropDownPersons);
        flexLayoutSettings(flexLayout);

        return flexLayout;
    }

    private void dropDownPersonsSettings() {
        dropDownPersons.setItemLabelGenerator(Person::getSecondName);
        dropDownPersons.setItems(allPersons);
    }

    private void addClickAction_AddAnotherPerson(Button button, List<Person> personList, FlexLayout fl) {
        button.addClickListener(event -> {
            ComboBox<Person> dropDownPersons2 = new ComboBox<>("Additional Person");
            dropDownPersons2.setItems(personList);
            dropDownPersons2.setItemLabelGenerator(Person::getSecondName);
            fl.add(dropDownPersons2);
        });
    }

    private void flexLayoutSettings(FlexLayout flexLayout) {
        flexLayout.setMaxWidth("90%");
        flexLayout.setAlignItems(Alignment.END);
        flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        flexLayout.setAlignContent(FlexLayout.ContentAlignment.CENTER);
        flexLayout.setJustifyContentMode(JustifyContentMode.AROUND);
        flexLayout.setWidthFull();
        flexLayout.setClassName("PersonsComboBoxes");
    }


}
