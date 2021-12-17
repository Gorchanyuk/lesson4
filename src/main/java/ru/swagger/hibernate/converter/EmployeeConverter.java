package ru.swagger.hibernate.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.swagger.hibernate.dto.EmployeeDTO;
import ru.swagger.hibernate.entity.Employee;

@Component
public class EmployeeConverter implements Converter<Employee, EmployeeDTO> {


    @Override
    public EmployeeDTO convert(Employee source) {
        return EmployeeDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .role(source.getRole())
                .salary(source.getSalary())
                .build();
    }
}
