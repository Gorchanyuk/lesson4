package ru.swagger.hibernate.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.swagger.hibernate.dto.EmployeeDTO;
import ru.swagger.hibernate.dto.OrganizationDTO;
import ru.swagger.hibernate.entity.Employee;
import ru.swagger.hibernate.entity.Organization;

import java.util.ArrayList;

@Component
public class OrganizationConverter implements Converter<Organization, OrganizationDTO> {
    @Override
    public OrganizationDTO convert(Organization source) {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setId(source.getId());
        organizationDTO.setName(source.getName());

        organizationDTO.setEmployees(new ArrayList<>());

        for(Employee employee : source.getEmployees()){
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId((employee.getId()));
            employeeDTO.setName(employee.getName());
            employeeDTO.setRole(employee.getRole());
            employeeDTO.setSalary(employee.getSalary());

            organizationDTO.getEmployees().add(employeeDTO);
        }
        return organizationDTO;
    }
}
