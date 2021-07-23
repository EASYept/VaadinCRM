package com.easyept.CrmForWork.entity;

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

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfTrip;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endOfTrip;

    public BusinessTrip(List<Person> persons, Factory factory, Date dateOfTrip, Date endOfTrip) {
        this.persons = persons;
        this.factory = factory;
        this.dateOfTrip = dateOfTrip;
        this.endOfTrip = endOfTrip;
    }

    //lombok getters and setters

}
