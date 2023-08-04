package com.employee.management.dao;

import com.employee.management.dto.DatabaseConfiguration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao {
    private final DatabaseConfiguration databaseConfiguration;

    public EmployeeDaoImpl(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    @Override
    public void insertData() {
        String csvFilePath = "src/main/resources/Data/Salary_Data.csv";
        String sql = "INSERT INTO employee (age, gender, education_level, job_title, years_of_experience, salary) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = databaseConfiguration.getJdbcTemplate().getDataSource().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            boolean isFirstLine = true; // Flag to skip the header row

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header row
                }

                String[] data = line.split(",");


                if (data.length != 6) {
                    // Handle the error for an incomplete record
                    System.out.println("Incomplete record: " + line);
                    continue; // Skip this line and move to the next one
                }

                int age;
                try {
                    age = Integer.parseInt(data[0]);
                } catch (NumberFormatException e) {
                    // Handle the error for an invalid age value
                    System.out.println("Invalid value for age: " + data[0]);
                    continue; // Skip this line and move to the next one
                }

                String gender = data[1];
                String educationLevel = data[2];
                String jobTitle = data[3];

                double yearsOfExperience;
                try {
                    yearsOfExperience = Double.parseDouble(data[4]);
                } catch (NumberFormatException e) {
                    // Handle the error for an invalid years of experience value
                    System.out.println("Invalid value for years of experience: " + data[4]);
                    continue; // Skip this line and move to the next one
                }

                double salary;
                try {
                    salary = Double.parseDouble(data[5]);
                } catch (NumberFormatException e) {
                    // Handle the error for an invalid salary value
                    System.out.println("Invalid value for salary: " + data[5]);
                    continue; // Skip this line and move to the next one
                }

                preparedStatement.setInt(1, age);
                preparedStatement.setString(2, gender);
                preparedStatement.setString(3, educationLevel);
                preparedStatement.setString(4, jobTitle);
                preparedStatement.setDouble(5, yearsOfExperience);
                preparedStatement.setDouble(6, salary);

                preparedStatement.executeUpdate();
            }
            System.out.println("Data inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
