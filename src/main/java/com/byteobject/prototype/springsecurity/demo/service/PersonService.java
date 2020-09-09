package com.byteobject.prototype.springsecurity.demo.service;

import com.byteobject.prototype.springsecurity.demo.repository.PersonDO;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    public Optional<PersonBO> getPersonById(int id);

    public List<PersonBO> getAllPeople();

    public PersonBO createPerson(PersonBO personBO);

    public PersonBO updatePerson(PersonBO personBO);

    public void deletePersonById(int id);
}
