package ua.lviv.iot;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import ua.lviv.iot.business.impl.CsvBeanInserterImpl;
import ua.lviv.iot.csv.CsvReader;
import ua.lviv.iot.csv.CsvWriter;
import ua.lviv.iot.model.Invitation;

@SpringBootApplication
public class HubSpotConversationApplication implements CommandLineRunner {

    @Autowired
    CsvWriter csvWriter;
    @Autowired
    CsvReader csvReader;
    @Autowired
    CsvBeanInserterImpl beanInserter;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(HubSpotConversationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        csvWriter.fillCsvFile();
        csvReader.readCsv();
        beanInserter.saveBeans();
        csvReader.readRealtionships();
        beanInserter.assingRelationshipToBeans();
        System.exit(0);
    }

}
