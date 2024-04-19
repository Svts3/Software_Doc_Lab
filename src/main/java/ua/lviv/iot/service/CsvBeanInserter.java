package ua.lviv.iot.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.opencsv.exceptions.CsvException;

public interface CsvBeanInserter {
    void saveBeans() throws FileNotFoundException;

    void assingRelationshipToBeans() throws FileNotFoundException, IOException, CsvException;
}
