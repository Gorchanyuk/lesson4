package ru.swagger.hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor  //для конструктора который инициализирует final поля
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String role;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "Employee_languages",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "programming_language_id")}
    )
    private List<ProgrammingLanguage>  programmingLanguages;

    private BigDecimal salary;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;


}
