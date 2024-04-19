package ua.lviv.iot.controller;

import java.util.List;

import ua.lviv.iot.model.Conversation;
import ua.lviv.iot.model.Message;
import ua.lviv.iot.model.Person;
import ua.lviv.iot.model.User;

public interface ConversationController extends GeneralController<Conversation, Long> {
    
    Message sendMessage(Conversation conversation, Message message);

    Conversation createConversation(List<User> members, Conversation conversation);

    Conversation deleteConversation(Long conversationId);

    Conversation addMembers(Long conversationId, List<Person> members);

    Conversation removeMember(Long conversationId, Long userId);

    void redirectUserToConversation(Long conversationId, Long userId);


}
