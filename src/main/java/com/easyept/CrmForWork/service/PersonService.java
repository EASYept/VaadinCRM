package com.easyept.CrmForWork.service;


import com.easyept.CrmForWork.dao.PersonRepository;
import com.easyept.CrmForWork.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Iterable<Person> saveAll(List<Person> people) {
        return personRepository.saveAll(people);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person add(Person person) {
        return personRepository.save(person);
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }



}
