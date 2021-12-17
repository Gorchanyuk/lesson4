package ru.swagger.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProgrammingLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prog_lang_seq")
    @SequenceGenerator(name = "prog_lang_seq", allocationSize = 1)
    private Long id;

    private String name;

    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "programmingLanguages"
    )
    private List<Employee> employees;
}
