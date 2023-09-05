package com.albertsons.person;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class PersonApiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PersonApiApplication.class, args);
		PersonService personService = context.getBean(PersonService.class);

		Faker faker = new Faker();
		for (int i = 0; i < 10_000; i++) {
			Person person = new Person();
			Name name = faker.name();
			person.setFirstName(name.firstName());
			person.setLastName(name.lastName());

			personService.createPerson(person);
		}

		for (int i = 0; i < 100; i++) {
			Person person = new Person();
			Name name = faker.name();
			person.setFirstName(name.firstName());
			person.setLastName("Smithzers");

			personService.createPerson(person);
		}
	}

}
