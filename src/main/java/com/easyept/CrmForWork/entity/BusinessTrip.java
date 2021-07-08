package com.easyept.CrmForWork.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "businessTrip")
@Data
@NoArgsConstructor
public class BusinessTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long businessTrip_id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Person_BusinessTrip",
            joinColumns = @JoinColumn(name = "businessTrip_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    @ToString.Exclude
    private List<Person> persons = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "factory_id")
    @NotNull
    private Factory factory;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfTrip;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endOfTrip;

    public void addPerson(Person person) {
        if (persons == null) {
            persons = new ArrayList<>();
        }
        persons.add(person);
    }

    //lombok getters and setters

}
