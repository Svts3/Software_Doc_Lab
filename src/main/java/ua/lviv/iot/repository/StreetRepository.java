package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.iot.model.Street;

public interface StreetRepository extends JpaRepository<Street, Long> {

}
