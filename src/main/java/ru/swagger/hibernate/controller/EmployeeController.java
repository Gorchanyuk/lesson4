package ru.swagger.hibernate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.swagger.hibernate.dto.CreateEmployeeDTO;
import ru.swagger.hibernate.entity.Employee;
import ru.swagger.hibernate.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getEmployee() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employee")
    public List<Employee> createEmployee(@RequestBody @Validated CreateEmployeeDTO request) {
        employeeService.saveEmployee(request.getName(), request.getRole(), request.getSalary());
        return employeeService.getEmployees();
    }

    @DeleteMapping("/employee/{id}")
    public List<Employee> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployeeBYId(id);
        return employeeService.getEmployees();
    }
}
