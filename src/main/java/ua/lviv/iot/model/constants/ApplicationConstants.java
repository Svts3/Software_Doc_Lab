package ua.lviv.iot.model.constants;

import java.util.List;

import lombok.Getter;
import ua.lviv.iot.model.City;
import ua.lviv.iot.model.Company;
import ua.lviv.iot.model.Conversation;
import ua.lviv.iot.model.Country;
import ua.lviv.iot.model.Invitation;
import ua.lviv.iot.model.Message;
import ua.lviv.iot.model.Operator;
import ua.lviv.iot.model.Person;
import ua.lviv.iot.model.Street;
import ua.lviv.iot.model.User;
import ua.lviv.iot.repository.CityRepository;
import ua.lviv.iot.repository.CompanyRepository;
import ua.lviv.iot.repository.ConversationRepository;
import ua.lviv.iot.repository.CountryRepository;
import ua.lviv.iot.repository.InvitationRepository;
import ua.lviv.iot.repository.MessageRepository;
import ua.lviv.iot.repository.OperatorRepository;
import ua.lviv.iot.repository.StreetRepository;
import ua.lviv.iot.repository.UserRepository;

@Getter
public final class ApplicationConstants {

    public static final List<Class<?>> MODELS_CLASS_TYPES = List.of(User.class, Operator.class,
            City.class, Company.class,Street.class, Conversation.class, Invitation.class, Message.class,
            Country.class);

    public static final List<Class<?>> MODELS_REPOSITORIES = List.of(UserRepository.class,
            OperatorRepository.class, CityRepository.class, CompanyRepository.class,
            ConversationRepository.class, InvitationRepository.class, MessageRepository.class,
            CountryRepository.class, StreetRepository.class);

}
