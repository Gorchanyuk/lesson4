package ru.swagger.hibernate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.swagger.hibernate.entity.Employee;
import ru.swagger.hibernate.entity.Organization;
import ru.swagger.hibernate.entity.ProgrammingLanguage;
import ru.swagger.hibernate.repository.EmployeeRepository;
import ru.swagger.hibernate.repository.ProgrammingLanguageRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProgrammingLanguageRepository programmingLanguageRepository;

    public  List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee saveEmployee(String name, String role, BigDecimal salary, Organization organization, List<String> languages) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setRole(role);
        employee.setSalary(salary);
        employee.setOrganization(organization);
        employee.setProgrammingLanguages(new ArrayList<>());

        for (String language : languages) {
            ProgrammingLanguage pl = programmingLanguageRepository.findByName(language)
                    .orElseGet(()->{
                        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
                        programmingLanguage.setName(language);
                        return programmingLanguage;
                    });

            employee.getProgrammingLanguages().add(pl);
        }

//        if(true){
//            throw new RuntimeException("hakked");
//        }
        return employeeRepository.save(employee);
    }
    @Transactional
    public Employee saveEmployee(String name, String role, BigDecimal salary, List<String> languages) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setRole(role);
        employee.setSalary(salary);
        employee.setProgrammingLanguages(new ArrayList<>());

        for (String language : languages) {
            ProgrammingLanguage pl = programmingLanguageRepository.findByName(language)
                    .orElseGet(()->{
                        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
                        programmingLanguage.setName(language);
                        return programmingLanguage;
                    });

            employee.getProgrammingLanguages().add(pl);
        }

        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteEmployeeBYId(Long id) {
        employeeRepository.deleteById(id);

    }

    public Employee getEmployeeByName(String name) {
        return employeeRepository.getByName(name)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Employee> getEmployeeBySalaryGreaterThan(BigDecimal salary) {
        //реализация с помощью сортировки
//        return employeeRepository.findAllBySalaryGreaterThan(salary,
//                  Sort.by(Sort.Order.asc("salary")));

        //реализация с пагинацией и сортировкой(получаем страницу 1 с 1й записью)
        return employeeRepository.findAllBySalaryGreaterThan(salary,
                PageRequest.of(1, 1, Sort.Direction.ASC, "salary"))
                .getContent();
    }

    public List<Employee> getBySalary(BigDecimal salary){
        return employeeRepository.findAllBySalary(salary);
    }

    public List<Employee> getByPl(String programmingLanguage) {
        return employeeRepository.findAllByProgrammingLanguagesName(programmingLanguage);
    }
}
