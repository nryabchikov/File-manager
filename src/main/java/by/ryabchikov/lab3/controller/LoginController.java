package by.ryabchikov.lab3.controller;

import by.ryabchikov.lab3.model.Person;
import by.ryabchikov.lab3.service.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
class LoginController {

    private PeopleService peopleService;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/new")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/new")
    public String addPerson(@RequestBody Person person) {
        peopleService.addPerson(person);
        return "redirect:/";
    }
}
