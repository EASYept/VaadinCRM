package com.easyept.CrmForWork.Views;

import com.easyept.CrmForWork.Views.UI.SkeletOfApp;
import com.easyept.CrmForWork.entity.BusinessTrip;
import com.easyept.CrmForWork.entity.Person;
import com.easyept.CrmForWork.service.BusinessTripService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.joda.time.LocalDate;

import java.sql.Date;
import java.util.List;
import java.util.StringJoiner;


@Route(value = "trips", layout = SkeletOfApp.class)
public class BusinessTripView extends VerticalLayout {

    private BusinessTripService bTripService;

    Button creteTrip = new Button(new Icon(VaadinIcon.PLUS_SQUARE_O));
    Button deleteTrip = new Button(new Icon(VaadinIcon.MINUS_SQUARE_O));
    TextField filterForGrid = new TextField();
    Grid<BusinessTrip> businessTripGrid = new Grid<>(BusinessTrip.class);

    public BusinessTripView(BusinessTripService bTripService) {
        this.bTripService = bTripService;

        setSizeFull();
        configureGrid(businessTripGrid);
        configureFilter(filterForGrid);

        add(new HorizontalLayout(creteTrip, deleteTrip)); //buttons
        createTripButtonConfig();
        deleteTripButtonConfig();

        add(filterForGrid);
        add(businessTripGrid);
        updateGridItems();
    }

    private void configureFilter(TextField filter) {
        filter.setPlaceholder("Filter by date..");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.setValueChangeTimeout(500);
        filter.addValueChangeListener(e -> updateGridItems());
    }

    private void deleteTripButtonConfig() {
        deleteTrip.addClickListener(event -> {
            businessTripGrid.getSelectedItems().forEach(trip -> bTripService.delete(trip));
            UI.getCurrent().navigate(BusinessTripView.class);
            updateGridItems();
        });
    }

    private void createTripButtonConfig() {
        creteTrip.addClickListener(event -> UI.getCurrent().navigate(BusinessTripsCreateForm2.class));
    }

    private void updateGridItems() {
        List<BusinessTrip> list = bTripService.findAllByText(filterForGrid.getValue());
        businessTripGrid.setItems(list);

    }

    private void configureGrid(Grid<BusinessTrip> btGrid) {
        btGrid.addClassName("businessTrip-view");
        btGrid.setSizeFull();

        //redo columns
        btGrid.removeAllColumns();
        btGrid.addColumn(trip -> {
                Date d  = trip.getDateOfTrip();
                return new LocalDate(d);
                                        }).setHeader("Date of Trip")
                                            .setSortable(true);
        btGrid.addColumn(trip -> {
                Date end  = trip.getEndOfTrip();
                return new LocalDate(end);
                                        }).setHeader("End of Trip")
                                            .setSortable(true);
        btGrid.addColumn(
                trip -> trip.getFactory()
                            .getFactoryName())
                            .setHeader("Factory")
                            .setSortable(true);
        btGrid.addColumn(trip -> {
                //create List<Persons> -> string with comma delimiter
                List<Person> persons = trip.getPersons();
                StringJoiner joiner = new StringJoiner(", ");
                persons.forEach(person -> joiner.add(person.getSecondName()));
                return joiner.toString();
                            }).setHeader("Persons")
                                .setSortable(true);

    }
}
