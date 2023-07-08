package com.employee.management.dao;

import com.employee.management.dto.DatabaseConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class DAOService {

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfiguration databaseConfiguration;

    public DAOService(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.databaseConfiguration = getDatabaseConfig();
    }

    private DatabaseConfiguration getDatabaseConfig() {
        return DatabaseConfiguration.builder()
                .jdbcTemplate(jdbcTemplate)
                .build();
    }


    public EmployeeDao getEmployeeDao() {
        return new EmployeeDaoImpl(databaseConfiguration);
    }
}
