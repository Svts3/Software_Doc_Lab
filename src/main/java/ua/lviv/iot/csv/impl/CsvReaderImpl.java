package ua.lviv.iot.csv.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBeanBuilder;

import ua.lviv.iot.csv.CsvReader;
import ua.lviv.iot.model.constants.ApplicationConstants;

@Component
public class CsvReaderImpl implements CsvReader {

    @Override
    public Map<?, List<?>> readCsv() throws FileNotFoundException {
        var classes = ApplicationConstants.MODELS_CLASS_TYPES;

        Map<Class<?>, List<?>> result = new HashMap<>();
        for (var class1 : classes) {
            var builder = new CsvToBeanBuilder<>(new FileReader(class1.getSimpleName() + ".csv"));
            List<?> list = builder.withType(class1).build().parse();
            result.put(class1, list);
        }

        return result;

    }

}
