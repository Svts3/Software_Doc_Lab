package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.iot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
