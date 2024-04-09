package ua.lviv.iot.business;

import java.io.FileNotFoundException;

public interface CsvBeanInserter {
    void saveBeans() throws FileNotFoundException;
}
