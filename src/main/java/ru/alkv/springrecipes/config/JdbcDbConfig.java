package ru.alkv.springrecipes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@PropertySource("classpath:jdbc2.properties")
public class JdbcDbConfig {
    @Value("${driverClassName}")
    private String driverClassName;

    @Value("${url}")
    private String url;

    @Value("${dbusername}")
    private String username;

    @Value("${dbpassword}")
    private String password;

    public static PropertySourcesPlaceholderConfigurer propertySourcesConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Lazy
    @Bean
    public DataSource dataSource() {
        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName(driverClassName);

            dataSource.setDriver(driver.newInstance());
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            return dataSource;
        }
        catch (Exception exception) {
            return null;
        }
    }
}
