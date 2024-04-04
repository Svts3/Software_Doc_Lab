package ua.lviv.iot.service;

import java.io.FileNotFoundException;

public interface CsvBeanInserter {
    void saveBeans() throws FileNotFoundException;
}
