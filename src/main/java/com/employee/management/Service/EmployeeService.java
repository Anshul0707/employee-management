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
    File file = new File(JSON_FILE_PATH);
    private long nextId = 1;

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
        List<Employee> employees = getAllEmployees();
        employees.removeIf(employee -> employee.getId().equals(id));
        employees.add(updatedEmployee);
        saveEmployees(employees);
    }

    public void deleteEmployee(Long id) {
        List<Employee> employees = getAllEmployees();
        employees.removeIf(employee -> employee.getId().equals(id));
        saveEmployees(employees);
    }

    private void saveEmployees(List<Employee> employees) {
        try {
            objectMapper.writeValue(file, employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

