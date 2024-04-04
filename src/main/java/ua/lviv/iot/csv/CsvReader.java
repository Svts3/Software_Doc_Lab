package ua.lviv.iot.csv;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface CsvReader {

    Map<?, List<?>> readCsv() throws FileNotFoundException;
}
