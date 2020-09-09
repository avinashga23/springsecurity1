package com.byteobject.prototype.springsecurity.demo.repository

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.HashMap

@Repository("personRepository")
open class PersonRepositoryMapImpl: PersonRepository {

    var id = 0;

    companion object {
        private val peopleMap = HashMap<Int, PersonDO>();
        private val LOGGER = LoggerFactory.getLogger(PersonRepositoryMapImpl::class.java)
    }

    override fun getPersonById(id: Int): Optional<PersonDO> {
        LOGGER.info("getting person by id $id")
        return Optional.ofNullable(peopleMap[id]);
    }

    override fun getAllPeople(): MutableList<PersonDO> {
        LOGGER.info("getting all people")
        return peopleMap.entries.stream().map { it.value }.collect(Collectors.toList());
    }

    override fun createPerson(personDO: PersonDO?): PersonDO {
        LOGGER.info("creating person $personDO")
        personDO?.id = ++id
        peopleMap[id] = personDO!!
        return personDO
    }

    override fun updatePerson(personDO: PersonDO?): PersonDO {
        LOGGER.info("updating person $personDO")
        peopleMap[personDO?.id!!] = personDO!!
        return personDO
    }

    override fun deletePersonById(id: Int) {
        LOGGER.info("deleting person $id")
        peopleMap.remove(id)
    }

}