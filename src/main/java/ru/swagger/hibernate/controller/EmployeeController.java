package ru.swagger.hibernate.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.swagger.hibernate.dto.CreateEmployeeDTO;
import ru.swagger.hibernate.dto.EmployeeDTO;
import ru.swagger.hibernate.entity.Employee;
import ru.swagger.hibernate.service.EmployeeService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ConversionService conversionService;

    @GetMapping("/employee")
    public List<Employee> getEmployee() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employee/{id}")
    public EmployeeDTO getEmployee(@PathVariable("id") Long id) {
        return conversionService.convert(employeeService.getEmployeeById(id), EmployeeDTO.class);
    }

    @GetMapping("/employee/find/name")
    public EmployeeDTO getEmployeeByName(@RequestParam("name") String name){
        return conversionService.convert(employeeService.getEmployeeByName(name), EmployeeDTO.class);
    }

    @GetMapping("/employee/find/salary")
    public List<EmployeeDTO> getEmployeeBySalary(@RequestParam("salary") BigDecimal salary){
        return employeeService.getEmployeeBySalaryGreaterThan(salary)
                .stream()
                .map(employee -> conversionService.convert(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/employee")
    public List<Employee> createEmployee(@RequestBody @Validated CreateEmployeeDTO request) {
        employeeService.saveEmployee(request.getName(), request.getRole(), request.getSalary(), request.getProgrammingLaguages());
        return employeeService.getEmployees();
    }

    @DeleteMapping("/employee/{id}")
    public List<Employee> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployeeBYId(id);
        return employeeService.getEmployees();
    }

    @GetMapping("/employee/find")
    public List<EmployeeDTO> getBySalary(@RequestParam("salary") BigDecimal salary){
        return employeeService.getBySalary(salary)
                .stream()
                .map(employee -> conversionService.convert(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/pl")
    public List<EmployeeDTO> getByPl(@RequestParam("pl") String programmingLanguage){
        return employeeService.getByPl(programmingLanguage)
                .stream()
                .map(employee -> conversionService.convert(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }
}
