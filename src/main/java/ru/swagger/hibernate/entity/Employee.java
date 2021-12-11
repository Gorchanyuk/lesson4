package ru.swagger.hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
//@RequiredArgsConstructor  для конструктора который инициализирует final поля
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String role;
    private BigDecimal salary;
}
