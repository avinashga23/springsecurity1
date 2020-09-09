package com.byteobject.prototype.springsecurity.demo.controller;

import com.byteobject.prototype.springsecurity.demo.service.PersonBO;
import com.byteobject.prototype.springsecurity.demo.service.PersonService;
import com.github.dozermapper.core.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController("personController")
@RequestMapping("/api/v1/person")
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    private Mapper mapper;

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService, Mapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('person:read', 'person:read_write')")
    public PersonDTO getPersonById(@PathVariable("id") int id) {
        LOGGER.info("get person by id {}", id);
        return personService.getPersonById(id).map(p -> mapper.map(p, PersonDTO.class)).get();
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('person:read', 'person:read_write')")
    public List<PersonDTO> getAllPeople() {
        LOGGER.info("get all people");
        return personService.getAllPeople().stream().map(bo -> mapper.map(bo, PersonDTO.class)).collect(Collectors.toList());
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('person:read_write')")
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO, UriComponentsBuilder uriComponentsBuilder) {
        LOGGER.info("create person {}", personDTO);
        var created = mapper.map(personService.createPerson(mapper.map(personDTO, PersonBO.class)), PersonDTO.class);
        var uri = uriComponentsBuilder.path("/api/v1/person/{id}").buildAndExpand(created.getId());
        return ResponseEntity.created(uri.toUri()).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('person:read_write')")
    public PersonDTO updatePerson(@PathVariable("id") int id, @RequestBody PersonDTO personDTO) {
        LOGGER.info("update person {}", personDTO);
        personDTO.setId(id);
        return mapper.map(personService.updatePerson(mapper.map(personDTO, PersonBO.class)), PersonDTO.class);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('person:read_write')")
    public void deletePerson(@PathVariable("id") int id) {
        LOGGER.info("delete person {}", id);

        personService.deletePersonById(id);
    }

}
