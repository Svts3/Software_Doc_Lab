package ua.lviv.iot.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.exceptions.CsvException;

import ua.lviv.iot.constants.ApplicationConstants;
import ua.lviv.iot.data.CsvReader;
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
import ua.lviv.iot.service.CsvBeanInserter;

@Service
public class CsvBeanInserterImpl implements CsvBeanInserter {

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

        List<Operator> operatorRecords = (List<Operator>) map.get(Operator.class);
        if (operatorRecords != null) {
            operatorRepository.saveAll(operatorRecords);
        }
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
            } else if (clazz.equals(Street.class)) {
                streetRepository.saveAll((List<Street>) records);
            } else if (clazz.equals(User.class)) {
                userRepository.saveAll((List<User>) records);
            }
        });

    }

    public void assingRelationshipToBeans()
            throws FileNotFoundException, IOException, CsvException {

        Map<Class<?>, Map<String, List<Long>>> map = csvReader.readRealtionships();

        var repos = ApplicationConstants.MODELS_REPOSITORIES;

        for (Map.Entry<Class<?>, Map<String, List<Long>>> entry : map.entrySet()) {

            Class class1 = entry.getKey();

            if (class1.getSimpleName().equals("City")) {

                List<Long> foreignKeys = entry.getValue().get("country");
                List<City> cities = cityRepository.findAll();
                for (int i = 0; i < cities.size(); i++) {
                    cities.get(i).setCountry(countryRepository.findById(foreignKeys.get(i)).get());
                    cityRepository.save(cities.get(i));
                }

            }
            if (class1.getSimpleName().equals("Message")) {
                List<Long> conversationKeys = entry.getValue().get("conversation");
                List<Message> messages = messageRepository.findAll();
                for (int i = 0; i < messages.size(); i++) {
                    messages.get(i).setConversation(
                            conversationRepository.findById(conversationKeys.get(i)).orElse(null));
                    messageRepository.save(messages.get(i));
                }

            }
            if (class1.getSimpleName().equals("Invitation")) {
                List<Long> conversations = entry.getValue().get("conversation");
                List<Long> invitedOperators = entry.getValue().get("invitedOperator");
                List<Invitation> invitations = invitationRepository.findAll();
                for (int i = 0; i < invitations.size(); i++) {
                    invitations.get(i).setConversation(
                            conversationRepository.findById(conversations.get(i)).get());
                    invitations.get(i).setInvitedOperator(
                            operatorRepository.findById(invitedOperators.get(i)).orElse(null));
                    invitationRepository.save(invitations.get(i));
                }
            }
            if (class1.getSimpleName().equals("Street")) {
                List<Long> foreignKeys = entry.getValue().get("city");
                List<Street> streets = streetRepository.findAll();
                for (int i = 0; i < streets.size(); i++) {
                    streets.get(i).setCity(cityRepository.findById(foreignKeys.get(i)).get());
                    streetRepository.save(streets.get(i));
                }

            }
            if (class1.getSimpleName().equals("Company")) {
                List<Long> foreignKeys = entry.getValue().get("street");
                List<Company> companies = companyRepository.findAll();
                for (int i = 0; i < companies.size(); i++) {
                    companies.get(i).setStreet(streetRepository.findById(foreignKeys.get(i)).get());
                    companyRepository.save(companies.get(i));
                }

            }

            if (class1.getSimpleName().equals("Operator")) {

                List<Long> foreignKeys = entry.getValue().get("company");
                List<Operator> operators = operatorRepository.findAll();
                for (int i = 0; i < operators.size(); i++) {
                    operators.get(i)
                            .setCompany(companyRepository.findById(foreignKeys.get(i)).get());
                    operatorRepository.save(operators.get(i));
                }

            }

        }

    }
}
