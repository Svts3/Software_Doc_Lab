package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.iot.model.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

}
