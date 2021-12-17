package ru.swagger.hibernate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data  // создает гетеры сетеры, методы equals и hashcode
public class CreateEmployeeDTO {
//    @NotEmpty//проверяет коллекции
    @NotBlank//проверяет не пустая ли строка
    @Schema(description = "Имя сотрудника", example = "Вася", required = true)//для swaggerа (больше нигде не участвует)
    private String name;

    @NotBlank
    private String role;

    @NotNull
    @Min(value = 25000)
    private BigDecimal salary;

    @NotEmpty
    private List<String> programmingLaguages;
}
