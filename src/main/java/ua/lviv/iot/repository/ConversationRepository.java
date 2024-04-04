package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.iot.model.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

}
