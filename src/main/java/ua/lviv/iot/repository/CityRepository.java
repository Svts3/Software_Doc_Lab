package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.iot.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
