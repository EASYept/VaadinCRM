package com.easyept.CrmForWork.Views;


import com.easyept.CrmForWork.Views.UI.SkeletOfApp;
import com.easyept.CrmForWork.entity.Person;
import com.easyept.CrmForWork.service.PersonService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import javax.transaction.Transactional;
import java.util.List;

@Route(value = "/persons", layout = SkeletOfApp.class)
public class PersonView extends VerticalLayout {

    private GridCrud<Person> crud = new GridCrud<>(Person.class);

    public PersonView(PersonService personService) {

        crud.setFindAllOperation(personService::findAll);
        crud.setAddOperation(personService::add);
        crud.setUpdateOperation(personService::update);
        crud.setDeleteOperation(personService::delete);
        crud.getGrid().removeColumnByKey("businessTrips");
        crud.getGrid().setColumns("person_id", "firstName", "secondName", "thirdName", "department");

        // TODO add Business trips column and make it nice looking https://www.youtube.com/watch?v=_109sgFRgA0&list=PLcRrh9hGNallPtT2VbUAsrWqvkQ-XE22h&index=8

        add(crud);
        setSizeFull();

    }




}
