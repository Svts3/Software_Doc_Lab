package ua.lviv.iot.data.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import ua.lviv.iot.constants.ApplicationConstants;
import ua.lviv.iot.data.CsvReader;

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

    @Override
    public Map<Class<?>, Map<String, List<Long>>> readRealtionships() throws IOException, CsvException {
        var classes = ApplicationConstants.MODELS_CLASS_TYPES;
        Map<Class<?>, Map<String, List<Long>>>result = new LinkedHashMap<>();
        for(var class1:classes) {

            CSVReader csvReader = new CSVReader(new FileReader(class1.getSimpleName()+".csv"));
        
            var csvContent = csvReader.readAll();
            
           result.put(class1, new HashMap<String, List<Long>>());
           int d = 0;
           for(var i:csvContent) {
             if(d++==0) {
                 continue;
             }
            if(class1.getSimpleName().equals("City")) {
                if(!result.get(class1).containsKey("country")) {
                    result.get(class1).put("country", new ArrayList<>());
                }
                result.get(class1).get("country").add(Long.valueOf(i[2]));
            }
            if(class1.getSimpleName().equals("Conversation")) {
                if(!result.get(class1).containsKey("messages")) {
                    result.get(class1).put("messages", new ArrayList<>());
                }
                result.get(class1).get("messages").add(Long.valueOf(i[3]));
            }
            if(class1.getSimpleName().equals("Invitation")) {
                if(!result.get(class1).containsKey("conversation")) {
                    result.get(class1).put("conversation", new ArrayList<>());
                }
                result.get(class1).get("conversation").add(Long.valueOf(i[0]));
                if(!result.get(class1).containsKey("invitedOperator")) {
                    result.get(class1).put("invitedOperator", new ArrayList<>());
                }
                result.get(class1).get("invitedOperator").add(Long.valueOf(i[3]));
            }
            if(class1.getSimpleName().equals("Message")) {
                if(!result.get(class1).containsKey("conversation")) {
                    result.get(class1).put("conversation", new ArrayList<>());
                }
                result.get(class1).get("conversation").add(Long.valueOf(i[1]));
            }
            if(class1.getSimpleName().equals("Street")) {
                if(!result.get(class1).containsKey("city")) {
                    result.get(class1).put("city", new ArrayList<>());
                }
                result.get(class1).get("city").add(Long.valueOf(i[2]));
            }
            if(class1.getSimpleName().equals("Company")) {
                if(!result.get(class1).containsKey("street")) {
                    result.get(class1).put("street", new ArrayList<>());
                }
                result.get(class1).get("street").add(Long.valueOf(i[0]));
            }
            
            if(class1.getSimpleName().equals("Operator")) {
                if(!result.get(class1).containsKey("company")) {
                    result.get(class1).put("company", new ArrayList<>());
                }
                result.get(class1).get("company").add(Long.valueOf(i[4]));
            }
               
               
           }
           
        }
        return result;
    }
}
