package ua.lviv.iot.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.opencsv.exceptions.CsvException;

public interface CsvReader {

    Map<?, List<?>> readCsv() throws FileNotFoundException;
    
    
    Map<Class<?>, Map<String, List<Long>>> readRealtionships()throws FileNotFoundException, IOException, CsvException;
}
