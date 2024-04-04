package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.iot.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
