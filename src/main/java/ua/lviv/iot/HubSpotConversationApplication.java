package ua.lviv.iot;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ua.lviv.iot.data.CsvReader;
import ua.lviv.iot.data.CsvWriter;
import ua.lviv.iot.service.CsvBeanInserter;

@SpringBootApplication
public class HubSpotConversationApplication implements CommandLineRunner {

    private CsvWriter csvWriter;
    private CsvReader csvReader;
    private CsvBeanInserter beanInserter;

    @Autowired
    public HubSpotConversationApplication(CsvWriter csvWriter, CsvReader csvReader,
            CsvBeanInserter beanInserter) {
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
        this.beanInserter = beanInserter;
    }

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

        System.out.println("DONE");
    }

}
