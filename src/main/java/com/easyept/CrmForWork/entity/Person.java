package com.easyept.CrmForWork.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long person_id;
    @NotBlank(message="Name is required")
    private String firstName;
    @NotBlank(message="Name is required")
    private String secondName;
    @NotBlank(message="Name is required")
    private String thirdName;
    @NotBlank(message="Department is required")
    private String department;

    // TODO Do all this logic, in the service layer, (with the @Transactional), not in the controller. There should be the right place to do this, it is part of the logic of the app, not in the controller (in this case, an interface to load the model). All the operations in the service layer should be transactional. i.e.: Move this line to the TopicService.findTopicByID method:
    //https://stackoverflow.com/questions/11746499/how-to-solve-the-failed-to-lazily-initialize-a-collection-of-role-hibernate-ex
    //    @Transactional doesn't helped should find another solution
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "person_businessTrip",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "businessTrip_id")
    )
    @ToString.Exclude
    private List<BusinessTrip> businessTrips = new ArrayList<>();

    public Person(String firstName, String secondName, String thirdName, String department) { //TODO understand how to create this in lombok
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.department = department;
    }

    public void addTrip(BusinessTrip trip) {
        if (businessTrips == null) {
            businessTrips = new ArrayList<>();
        }
        businessTrips.add(trip);
    }


    //lombok getters and setters
}
