package ru.swagger.hibernate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.swagger.hibernate.entity.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.name = :employeeName")
    Optional<Employee> getByName(@Param("employeeName") String name);

    //Пример создания запроса с JOIN
    @Query("FROM Employee e " +
            "LEFT JOIN e.programmingLanguages p " +
//            "LEFT JOIN FETCH e.programmingLanguages p " +  //FETCH подтянет все данные
            "WHERE p.name = :name")
    List<Employee> findAllByProgrammingLanguagesName(String name);

    //nativeQuery = true говорит что запрос пишем на SQL, в таком запросе нужно указывать имена так как они записаны в БД
    @Query(value = "SELECT * FROM employee where salary = :salary", nativeQuery = true)
    List<Employee> findAllBySalary(@Param("salary") BigDecimal salary);

    //реализация с добавлением сортировки
//    List<Employee> findAllBySalaryGreaterThan(BigDecimal salary, Sort sort);

    //реализация с пагинацией
    Page<Employee> findAllBySalaryGreaterThan(BigDecimal salary, Pageable request);


    @Modifying //ставится для запросов на изменение и удаление из бд
    @Transactional
    void deleteByName(String name);
}
