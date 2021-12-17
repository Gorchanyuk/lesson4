package ru.swagger.hibernate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.swagger.hibernate.dto.CreateEmployeeDTO;
import ru.swagger.hibernate.dto.EmployeeDTO;
import ru.swagger.hibernate.entity.Employee;
import ru.swagger.hibernate.entity.Organization;
import ru.swagger.hibernate.repository.OrganizationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private  final OrganizationRepository organizationRepository;
    private final EmployeeService employeeService;

    @Transactional
    public Organization saveOrganization(String name, List<CreateEmployeeDTO> employees){
        Organization organization = new Organization();
        organization.setName(name);

        Organization savedOrganization = organizationRepository.save(organization);


        for(CreateEmployeeDTO employee : employees){
            Employee savedEmployee = employeeService.saveEmployee(employee.getName(), employee.getRole(),
                    employee.getSalary(), savedOrganization, employee.getProgrammingLaguages()
            );
        }

        return  savedOrganization;
    }

    public List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }
}
