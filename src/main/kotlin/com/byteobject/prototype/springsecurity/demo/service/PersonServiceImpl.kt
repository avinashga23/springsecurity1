package com.byteobject.prototype.springsecurity.demo.service

import com.byteobject.prototype.springsecurity.demo.repository.PersonDO
import com.byteobject.prototype.springsecurity.demo.repository.PersonNotFoundException
import com.byteobject.prototype.springsecurity.demo.repository.PersonRepository
import com.github.dozermapper.core.Mapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service("personService")
class PersonServiceImpl(private val personRepository: PersonRepository, private val mapper: Mapper): PersonService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(PersonServiceImpl::class.java)
    }

    override fun getPersonById(id: Int): Optional<PersonBO> {
        LOGGER.info("getting person by id $id")
        val personDoOpt = personRepository.getPersonById(id)
        if (personDoOpt.isEmpty)
            throw PersonNotFoundException(PersonRepository.PERSON_BY_ID_NOT_FOUND, listOf(id))
        return personDoOpt.map { mapper.map(it, PersonBO::class.java) }
    }

    override fun getAllPeople(): MutableList<PersonBO> {
        LOGGER.info("get all people")
        return personRepository.allPeople.stream().map { mapper.map(it, PersonBO::class.java) }.collect(Collectors.toList())
    }

    override fun createPerson(personBO: PersonBO?): PersonBO {
        LOGGER.info("create person $personBO")
        return mapper.map(personRepository.createPerson(mapper.map(personBO, PersonDO::class.java)), PersonBO::class.java)
    }

    override fun updatePerson(personBO: PersonBO?): PersonBO {
        LOGGER.info("update person $personBO")
        if(personRepository.getPersonById(personBO!!.id).isEmpty)
            throw PersonNotFoundException(PersonRepository.PERSON_BY_ID_NOT_FOUND, listOf(personBO.id))
        return mapper.map(personRepository.updatePerson(mapper.map(personBO, PersonDO::class.java)), PersonBO::class.java)
    }

    override fun deletePersonById(id: Int) {
        LOGGER.info("delete person by id $id")
        return personRepository.deletePersonById(id)
    }

}