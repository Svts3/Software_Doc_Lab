package ua.lviv.iot.service;

import ua.lviv.iot.model.Conversation;

public interface ConversationService extends GeneralService<Conversation, Long> {

    Conversation addMember(String username, Long conversationId);
    Conversation removeMember(String username, Long conversationId);
}
