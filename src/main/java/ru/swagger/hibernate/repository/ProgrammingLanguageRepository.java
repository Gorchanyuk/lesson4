package ru.swagger.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.swagger.hibernate.entity.ProgrammingLanguage;

import java.util.Optional;

@Repository
public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Long> {

    Optional<ProgrammingLanguage> findByName(String name);
}
