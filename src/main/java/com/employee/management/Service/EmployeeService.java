package com.employee.management.Service;

import com.employee.management.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private static final String JSON_FILE_PATH = "src/main/resources/Data/employees.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    static File file = new File(JSON_FILE_PATH);
    private long nextId = 1;

    static {
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    public List<Employee> getAllEmployees() {
        try {
            return objectMapper.readValue(file, new TypeReference<List<Employee>>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public Employee getEmployeeById(Long id) {
        for (Employee employee : getAllEmployees()) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public void addEmployee(Employee employee) {
        List<Employee> employees = getAllEmployees();
        employee.setId(nextId++);
        employees.add(employee);
        saveEmployees(employees);
    }

    public void updateEmployee(Long id, Employee updatedEmployee) {
        deleteEmployee(id);
        List<Employee> employees = getAllEmployees();
//        employees.removeIf(employee -> employee.getId().equals(id));
        updatedEmployee.setId(id);
        employees.add(updatedEmployee);
        saveEmployees(employees);
    }

    public void deleteEmployee(Long id) {
        List<Employee> employees = getAllEmployees();
        List<Employee> removeEmployees = new ArrayList<>();
//        employees.removeIf(employee -> employee.getId().equals(id));
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                removeEmployees.add(employee);
            }
        }
        employees.removeAll(removeEmployees);
        saveEmployees(employees);
    }

    private void saveEmployees(List<Employee> employees) {
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            objectMapper.writeValue(file, employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

