package by.ryabchikov.lab3.service;

import by.ryabchikov.lab3.model.Person;
import by.ryabchikov.lab3.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PeopleService {
    private PeopleRepository peopleRepository;
    private PasswordEncoder passwordEncoder;

    public void addPerson(Person person) {
        person.setRoles("ROLE_" + person.getRoles());
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        peopleRepository.save(person);
    }
}
