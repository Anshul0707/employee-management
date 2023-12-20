package com.employee.management.Service;

import com.employee.management.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class FileHandler {
    private static final String JSON_FILE_PATH = "src/main/resources/Data/employees.json";
    private static final File file = new File(JSON_FILE_PATH);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    public static List<Employee> readEmployeesFromFile() {
        try {
            if (!file.exists()) {
                file.createNewFile();
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void writeEmployeesToFile(List<Employee> employees) {
        try {
            objectMapper.writeValue(file, employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
