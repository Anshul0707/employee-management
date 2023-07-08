package com.employee.management.model;

import lombok.*;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int age;
    private String gender;
    private String educationLevel;
    private String jobTitle;
    private int yearsOfExperience;
    private double salary;
}
