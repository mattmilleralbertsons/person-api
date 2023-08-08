package com.albertsons.person;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Transactional
    public Person getPerson(UUID personId) {
        return personDao.getPerson(personId);
    }

    @Transactional
    public Person createPerson(Person person) {
        if (person.getPersonId() != null) {
            throw new PersonException("Do not provide a personId when creating a new record.");
        }

        Person updated = Person.copy(person);
        updated.setPersonId(UUID.randomUUID());

        personDao.createPerson(updated);
        log.info("Created person. person={}", updated);

        return updated;
    }
}
