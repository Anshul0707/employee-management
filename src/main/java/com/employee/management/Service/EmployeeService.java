package com.employee.management.Service;

import com.employee.management.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private long nextId = 1;

    @Autowired
    private FileHandler fileHandler;


    public List<Employee> getAllEmployees() {
        return FileHandler.readEmployeesFromFile();
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
        FileHandler.writeEmployeesToFile(employees);
    }

    public void updateEmployee(Long id, Employee updatedEmployee) {
        deleteEmployee(id);
        List<Employee> employees = getAllEmployees();
        updatedEmployee.setId(id);
        employees.add(updatedEmployee);
        FileHandler.writeEmployeesToFile(employees);
    }

    public void deleteEmployee(Long id) {
        List<Employee> employees = getAllEmployees();
        List<Employee> removeEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                removeEmployees.add(employee);
            }
        }
        employees.removeAll(removeEmployees);
        FileHandler.writeEmployeesToFile(employees);
    }
}

