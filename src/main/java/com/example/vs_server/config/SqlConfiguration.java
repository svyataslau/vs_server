package com.example.vs_server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SqlConfiguration {

    @Value("#{environment.POSTGRES_USER}")
    private String user;
    @Value("#{environment.POSTGRES_PASSWORD}")
    private String password;
    @Value("#{environment.POSTGRES_DB}")
    private String db;

    @Bean
    public DataSource primary() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");

        dataSource.setUrl("jdbc:postgresql://spring_dev_db:5432/" + db);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }
}