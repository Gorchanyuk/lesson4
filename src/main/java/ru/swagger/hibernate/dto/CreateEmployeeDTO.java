package ru.swagger.hibernate.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data  // создает гетеры сетеры, методы equals и hashcode
public class CreateEmployeeDTO {
//    @NotEmpty//проверяет коллекции
    @NotBlank//проверяет не пустая ли строка
    private String name;
    @NotBlank
    private String role;
    @NotNull
    @Min(value = 25000)
    private BigDecimal salary;
}
