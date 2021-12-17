package ru.swagger.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrganizationDTO {

    private String name;
    private List<CreateEmployeeDTO> employees;
}
