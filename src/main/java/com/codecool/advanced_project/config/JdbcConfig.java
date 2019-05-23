package com.codecool.advanced_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.codecool.advanced_project")
public class JdbcConfig {

    @Bean
    public DataSource postgresqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(System.getenv("POSTGRES_DB_URL"));
        dataSource.setUsername(System.getenv("POSTGRES_DB_USER"));
        dataSource.setPassword(System.getenv("POSTGRES_DB_PASSWORD"));

        return dataSource;
    }

}