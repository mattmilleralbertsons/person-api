package com.albertsons.person;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Service
@Slf4j
public class PersonDao {
    private static final RowMapper<Person> PERSON_ROW_MAPPER = new RowMapper<Person>() {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setPersonId(UUID.fromString(rs.getString("person_id")));
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));

            return person;
        }
    };

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Person getPerson(UUID personId) {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT person_id, first_name, last_name FROM people WHERE person_id = ?",
                PERSON_ROW_MAPPER,
                personId.toString()
            );
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Person not found. personId=" + personId);
        }
    }

    public void createPerson(Person person) {
        jdbcTemplate.update(
            "INSERT INTO people (person_id, first_name, last_name) VALUES (?, ?, ?)",
            person.getPersonId(),
            person.getFirstName(),
            person.getLastName()
        );
    }
}
