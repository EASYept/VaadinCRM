package com.easyept.CrmForWork.controller;


import com.easyept.CrmForWork.entity.BusinessTrip;
import com.easyept.CrmForWork.entity.Factory;
import com.easyept.CrmForWork.entity.Person;
import com.easyept.CrmForWork.service.BusinessTripService;
import com.easyept.CrmForWork.service.FactoryService;
import com.easyept.CrmForWork.service.PersonService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;
    private FactoryService factoryService;
    private BusinessTripService tripService;


    @Autowired
    public PersonController(BusinessTripService tripService,
                            PersonService personService,
                            FactoryService factoryService) {
        this.tripService = tripService;
        this.personService = personService;
        this.factoryService = factoryService;
    }

    @Profile("dev")
    @PostConstruct
    private void loadData() {
        Person p1 = new Person("Sasha", "Kokshin","Kek", "otdel");
        Person p2 = new Person("Ali", "Bodiazhin","Keking","CoolPeople");
        Person p3 = new Person("Kuki", "Kurev","Keking","CoolPeople");
        Person p4 = new Person("Gugi", "Durov","Keking","CoolPeople");
        Person p5 = new Person("Pinky", "Ebobo","Keking","otdel");
        List<Person> personList = Arrays.asList(p1, p2, p3, p4, p5);

        Factory f1 = new Factory("NNK", "Novokuibishevsk");
        Factory f2 = new Factory("ANPZ", "Achinsk");
        List<Factory> factoryList = Arrays.asList(f1, f2);
        personService.saveAll(personList);
        factoryService.saveAll(factoryList);

        BusinessTrip b1 = new BusinessTrip();
        b1.setPersons(Arrays.asList(p1, p2));
        b1.setFactory(f1);
        b1.setDateOfTrip(new Date(new LocalDate().toDate().getTime()));
        b1.setEndOfTrip(new Date(new LocalDate().toDate().getTime()));
        tripService.add(b1);

        List<Person> personList1 = Arrays.asList(p3, p4);
        BusinessTrip b2 = new BusinessTrip(
                personList1,
                f2,
                new Date(new LocalDate().plusDays(2).toDate().getTime()),
                new Date(new LocalDate().plusDays(3).toDate().getTime()));
        tripService.add(b2);

        List<Person> personList2 = Arrays.asList(p4, p5);
        BusinessTrip b3 = new BusinessTrip(
                personList2,
                f2,
                new Date(new LocalDate().plusDays(6).toDate().getTime()),
                new Date(new LocalDate().plusDays(7).toDate().getTime()));
        tripService.add(b3);

        //past
        List<Person> personList4 = Arrays.asList(p4, p5);
        BusinessTrip b4 = new BusinessTrip(
                personList4,
                f1,
                new Date(new LocalDate().minusDays(30).toDate().getTime()),
                new Date(new LocalDate().minusDays(25).toDate().getTime()));
        tripService.add(b4);

        //future
        List<Person> personList5 = Arrays.asList(p4, p5);
        BusinessTrip b5 = new BusinessTrip(
                personList5,
                f2,
                new Date(new LocalDate().plusDays(30).toDate().getTime()),
                new Date(new LocalDate().plusDays(35).toDate().getTime()));
        tripService.add(b5);

    }








}
