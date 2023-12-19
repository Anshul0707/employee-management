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
    private static final String JSON_FILE_PATH = "Data/employees.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Employee> getAllEmployees() {
        try {
            return objectMapper.readValue( new File(JSON_FILE_PATH),new TypeReference<List<Employee>>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public Employee getEmployeeById(Long id) {
        return getAllEmployees().stream().filter(employee -> employee.getId().equals(id)).findFirst().orElse(null);
    }

    public void addEmployee(Employee employee) {
        List<Employee> employees = getAllEmployees();
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
            objectMapper.writeValue(new File(JSON_FILE_PATH), employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

