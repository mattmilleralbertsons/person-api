package com.albertsons.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private UUID personId;
    private String firstName;
    private String lastName;

    public static Person copy(Person person) {
        return new Person(person.getPersonId(), person.getFirstName(), person.getLastName());
    }
}
