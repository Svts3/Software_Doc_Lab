package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.iot.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
