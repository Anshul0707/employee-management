package com.employee.management;

import com.employee.management.dao.DAOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SalaryManagementApplication implements CommandLineRunner {

    @Autowired
    private DAOService daoService;

    public static void main(String[] args) {
        SpringApplication.run(SalaryManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Application is started ...");
        daoService.getEmployeeDao().insertData();
    }
}
