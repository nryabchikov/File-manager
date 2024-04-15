package by.ryabchikov.lab3.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;


}
