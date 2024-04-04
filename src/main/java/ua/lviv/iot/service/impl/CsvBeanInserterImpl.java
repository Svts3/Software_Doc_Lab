package ua.lviv.iot.service.impl;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.csv.CsvReader;
import ua.lviv.iot.model.City;
import ua.lviv.iot.model.Company;
import ua.lviv.iot.model.Conversation;
import ua.lviv.iot.model.Country;
import ua.lviv.iot.model.Invitation;
import ua.lviv.iot.model.Message;
import ua.lviv.iot.model.Operator;
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

@Service
public class CsvBeanInserterImpl {

    private CsvReader csvReader;
    private CityRepository cityRepository;
    private CompanyRepository companyRepository;
    private ConversationRepository conversationRepository;
    private CountryRepository countryRepository;
    private InvitationRepository invitationRepository;
    private MessageRepository messageRepository;
    private OperatorRepository operatorRepository;
    private StreetRepository streetRepository;
    private UserRepository userRepository;

    @Autowired
    public CsvBeanInserterImpl(CsvReader csvReader, CityRepository cityRepository,
            CompanyRepository companyRepository, ConversationRepository conversationRepository,
            CountryRepository countryRepository, InvitationRepository invitationRepository,
            MessageRepository messageRepository, OperatorRepository operatorRepository,
            StreetRepository streetRepository, UserRepository userRepository) {
        this.csvReader = csvReader;
        this.cityRepository = cityRepository;
        this.companyRepository = companyRepository;
        this.conversationRepository = conversationRepository;
        this.countryRepository = countryRepository;
        this.invitationRepository = invitationRepository;
        this.messageRepository = messageRepository;
        this.operatorRepository = operatorRepository;
        this.streetRepository = streetRepository;
        this.userRepository = userRepository;

    }

    public void saveBeans() throws FileNotFoundException {
        Map<Class<?>, List<?>> map = (Map<Class<?>, List<?>>) csvReader.readCsv();

        map.forEach((clazz, records) -> {
            if (clazz.equals(City.class)) {
                cityRepository.saveAll((List<City>) records);
            } else if (clazz.equals(Company.class)) {
                companyRepository.saveAll((List<Company>) records);
            } else if (clazz.equals(Conversation.class)) {
                conversationRepository.saveAll((List<Conversation>) records);
            } else if (clazz.equals(Country.class)) {
                countryRepository.saveAll((List<Country>) records);
            } else if (clazz.equals(Invitation.class)) {
                invitationRepository.saveAll((List<Invitation>) records);
            } else if (clazz.equals(Message.class)) {
                messageRepository.saveAll((List<Message>) records);
            } else if (clazz.equals(Operator.class)) {
                operatorRepository.saveAll((List<Operator>) records);
            } else if (clazz.equals(Street.class)) {
                streetRepository.saveAll((List<Street>) records);
            } else if (clazz.equals(User.class)) {
                userRepository.saveAll((List<User>) records);
            } else {
                System.out.println("Unsupported class: " + clazz.getName());
            }
        });

    }
}
