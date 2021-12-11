package ru.swagger.hibernate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.swagger.hibernate.dto.CreateEmployeeDTO;
import ru.swagger.hibernate.entity.Employee;
import ru.swagger.hibernate.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public  List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public void saveEmployee(String name, String role, BigDecimal salary) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setRole(role);
        employee.setSalary(salary);

        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteEmployeeBYId(Long id) {
        employeeRepository.deleteById(id);

    }
}
