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
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "/createTrip_v3", layout = SkeletOfApp.class)
public class BTCreateForm_v3 extends VerticalLayout {

    private PersonService personService;
    private FactoryService factoryService;
    private BusinessTripService businessTripService;

    private Button buttonAddAnotherPerson = new Button("Add another person");
    private ComboBox<Person> choosePerson = new ComboBox<>("Persons");
    private FlexLayout peopleFields = new FlexLayout();
    private ComboBox<Factory> chooseFactory = new ComboBox<>("Factories");
    private DatePicker startDate = new DatePicker("Starting Date");
    private DatePicker endDate = new DatePicker("End Date");
    private Button submitAllFieldsToTripAndSave = new Button("Submit");

    public BTCreateForm_v3(@Autowired PersonService personService,
                            @Autowired FactoryService factoryService,
                            @Autowired BusinessTripService businessTripService) {
        this.factoryService = factoryService;
        this.personService = personService;
        this.businessTripService = businessTripService;

        peopleFields.add(choosePerson);
        add(buttonAddAnotherPerson,
                peopleFields,
                chooseFactory,
                startDate,
                endDate,
                submitAllFieldsToTripAndSave
        );

        List<Person> allPeople = personService.findAll();
        configForChoosePerson(choosePerson, allPeople);
        configForButtonAddAnotherPerson(buttonAddAnotherPerson, peopleFields, allPeople);
        configForPeopleFields(peopleFields);
        configForChooseFactory(chooseFactory);
        configForStartDate(startDate);
        configForEndDate(endDate);
        configForSubmitAllFieldsToTripAndSave(submitAllFieldsToTripAndSave);

    }

    private void configForSubmitAllFieldsToTripAndSave(Button submitAllFieldsToTripAndSave) {
        BusinessTrip trip = new BusinessTrip();
        List<Person> allPersonsFromLayout = new ArrayList<>();
        submitAllFieldsToTripAndSave.addClickListener(event -> {
            List<Component> collectAllPersons = peopleFields.getChildren().collect(Collectors.toList());
            for (Component person : collectAllPersons) {
                if (person instanceof ComboBox) {
                    Object valueOfBox = ((ComboBox) person).getValue();
                    allPersonsFromLayout.add((Person) valueOfBox);
                }
            }
            //set fields
            trip.setPersons(allPersonsFromLayout.stream()
                    .distinct()
                    .collect(Collectors.toList())); // Deleting duplicates if presented
            trip.setFactory(chooseFactory.getValue());
            trip.setDateOfTrip(Date.valueOf(startDate.getValue()));
            trip.setEndOfTrip(Date.valueOf(endDate.getValue()));

            // saving BusinessTrip
            businessTripService.add(trip);

            //redirect to BusinessTripView
            UI.getCurrent().navigate(BusinessTripView.class);
        });

    }


    private void configForChoosePerson(ComboBox<Person> choosePerson, List<Person> providedList) {
        choosePerson.setItemLabelGenerator(Person::getSecondName);
        choosePerson.setItems(providedList);
    }

    private void configForStartDate(DatePicker startDate) {
    }
    private void configForEndDate(DatePicker endDate) {
    }

    private void configForChooseFactory(ComboBox<Factory> chooseFactory) {
        chooseFactory.setItemLabelGenerator(Factory::getFactoryName);
        chooseFactory.setItems(factoryService.findAll());
    }

    private void configForPeopleFields(FlexLayout peopleFields) {
        peopleFields.setMaxWidth("90%");
        peopleFields.setAlignItems(Alignment.END);
        peopleFields.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        peopleFields.setAlignContent(FlexLayout.ContentAlignment.CENTER);
        peopleFields.setJustifyContentMode(JustifyContentMode.AROUND);
        peopleFields.setWidthFull();
        peopleFields.setClassName("PersonsComboBoxes");
    }

    private void configForButtonAddAnotherPerson(Button button, FlexLayout flexLayout, List<Person> providedList) {
        button.addClickListener(event -> {
            ComboBox<Person> additionalPersonField = new ComboBox<>("Additional Person");
            additionalPersonField.setItems(providedList);
            additionalPersonField.setItemLabelGenerator(Person::getSecondName);
            flexLayout.add(additionalPersonField);
        });
        //visual settings
        button.setWidthFull();


    }


}
