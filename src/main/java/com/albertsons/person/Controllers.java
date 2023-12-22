package com.albertsons.person;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class Controllers {

    private final PersonService personService;

    public Controllers(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/people/{personId}")
    public ResponseEntity<Person> getPerson(@PathVariable("personId") String personId) {
        log.info("Getting person. personId={}", personId);
        Person person = personService.getPerson(UUID.fromString(personId));

        return ResponseEntity.ok(person);
    }

    @PostMapping("/people")
    public ResponseEntity<Person> postPerson(@RequestBody Person person) {
        Person created = personService.createPerson(person);

        return ResponseEntity.ok(created);
    }
}
