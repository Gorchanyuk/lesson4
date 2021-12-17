package ru.swagger.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {

    private Long id;

    private String name;

    private List<EmployeeDTO> employees;
}
