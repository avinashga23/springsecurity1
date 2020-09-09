package com.byteobject.prototype.springsecurity.demo.repository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    public static final String PERSON_BY_ID_NOT_FOUND = "person_by_id_not_found";

    public Optional<PersonDO> getPersonById(int id);

    public List<PersonDO> getAllPeople();

    public PersonDO createPerson(PersonDO personDO);

    public PersonDO updatePerson(PersonDO personDO);

    public void deletePersonById(int id);

}
