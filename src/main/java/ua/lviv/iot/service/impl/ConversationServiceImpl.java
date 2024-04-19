package ua.lviv.iot.service.impl;

import java.util.Collection;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.exception.ConversationNotFoundException;
import ua.lviv.iot.model.Conversation;
import ua.lviv.iot.model.Person;
import ua.lviv.iot.repository.ConversationRepository;
import ua.lviv.iot.repository.PersonRepository;
import ua.lviv.iot.service.ConversationService;

@Service
public class ConversationServiceImpl implements ConversationService {

    private ConversationRepository conversationRepository;

    private PersonRepository personRepository;

    @Autowired
    public ConversationServiceImpl(ConversationRepository conversationRepository,
            PersonRepository personRepository) {
        this.conversationRepository = conversationRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Conversation save(Conversation entity) {
        return conversationRepository.save(entity);
    }

    @Override
    public Collection<Conversation> findAll() {
        return conversationRepository.findAll().stream()
                .sorted(Comparator.comparing(Conversation::getId).reversed()).toList();
    }

    @Override
    public Conversation findById(Long id) {
        return conversationRepository.findById(id)
                .orElseThrow(() -> new ConversationNotFoundException(
                        String.format("Conversation [ID:%d] was not found!", id)));
    }

    @Override
    public Conversation update(Long id, Conversation entity) {

        Conversation conversation = findById(id);

        conversation.setDescription(entity.getDescription());
        conversation.setName(entity.getName());

        return conversationRepository.save(conversation);
    }

    @Override
    public Conversation deleteById(Long id) {
        Conversation conversation = findById(id);
        conversation.getInvitations().clear();
        conversation.getMessages().clear();
        conversation.getMembers().clear();
        conversationRepository.save(conversation);
        conversationRepository.deleteById(id);
        return conversation;

    }

    @Override
    public Conversation addMember(String username, Long conversationId) {
        Person person = personRepository.findByEmail(username);
        Conversation conversation = findById(conversationId);
        conversation.getMembers().add(person);
        return conversationRepository.save(conversation);
    }

    @Override
    public Conversation removeMember(String username, Long conversationId) {
        Person person = personRepository.findByEmail(username);
        Conversation conversation = findById(conversationId);
        conversation.getMembers().removeIf(i->i.getId().equals(person.getId()));
        return conversationRepository.save(conversation);
    }

}
