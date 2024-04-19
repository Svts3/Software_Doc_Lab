package ua.lviv.iot.data.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ua.lviv.iot.constants.ApplicationConstants;
import ua.lviv.iot.data.CsvWriter;

@Component
public class CsvWriterImpl implements CsvWriter {

    private Set<Long> ids = new HashSet<>();

    public void fillCsvFile() throws IOException {

        List<Class<?>> classes = ApplicationConstants.MODELS_CLASS_TYPES;

        Random random = new Random();

        for (var c : classes) {
            Set<Field> classFields = getAllFields(c);

            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(c.getSimpleName() + ".csv"))) {
                classFields = classFields.stream().sorted(Comparator.comparing(Field::getName))
                        .collect(Collectors.toSet());
                bufferedWriter.write(
                        classFields.stream().map(Field::getName).collect(Collectors.joining(",")));
                bufferedWriter.newLine();
                for (int r = 0; r < 1000; r++) {
                    int count = 0;
                    for (Field field : classFields) {
                        if(c.getSimpleName().equals("Conversation") && field.getName().equals("invitation")) {
                            continue;
                        }
                        generateRandomValue(classes, random, bufferedWriter, field);
                        if (++count < classFields.size()) {
                            bufferedWriter.write(",");
                        }
                    }
                    bufferedWriter.newLine();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void generateRandomValue(List<Class<?>> classes, Random random,
            BufferedWriter bufferedWriter, Field i) throws IOException {
        if (i.getType() == Long.class
                || (classes.contains(i.getType()) || i.getType() == List.class)) {

            int min = 1, max = 1001;
            Long randValue = random.nextLong(min, max);
            while (ids.contains(randValue)) {
                randValue = random.nextLong(min, max);
            }
            bufferedWriter.write(String.valueOf(randValue));

        } else if (i.getType() == Date.class) {
            long randomMillis = ThreadLocalRandom.current().nextLong(0, System.currentTimeMillis());
            Date randomDate = new Date(randomMillis);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(randomDate);
            bufferedWriter.write(formattedDate);

        } else if (i.getType() == String.class) {

            String generatedString = random.ints(97, 122 + 1).limit(10).collect(StringBuilder::new,
                    StringBuilder::appendCodePoint, StringBuilder::append).toString();
            bufferedWriter.write(generatedString);

        }

    }

    private Set<Field> getAllFields(Class<?> type) {
        Set<Field> fields = new HashSet<>();
        Class<?> currentType = type;

        while (currentType != null && currentType != Object.class) {
            fields.addAll(Arrays.asList(currentType.getDeclaredFields()));
            currentType = currentType.getSuperclass();
        }

        return fields;
    }

}
