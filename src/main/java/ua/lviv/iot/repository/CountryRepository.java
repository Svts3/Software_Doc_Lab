package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.iot.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
