package by.ryabchikov.lab3.service;

import by.ryabchikov.lab3.config.PersonDetails;
import by.ryabchikov.lab3.model.Person;
import by.ryabchikov.lab3.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleDetailsService implements UserDetailsService {
    @Autowired
    private PeopleRepository peopleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByName(username);
        return person.map(PersonDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
