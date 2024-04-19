package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.iot.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
    Person findByEmail(String email);
}
