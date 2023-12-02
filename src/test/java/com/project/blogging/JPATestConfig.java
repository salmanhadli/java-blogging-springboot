package com.project.blogging;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JPATestConfig {

  @Bean
  @Profile("test")
  public DataSource dataSource() {
    var dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.h2.Driver");
    dataSource.setUrl("jdbc:h2:file:./blog_app;DB_CLOSE_DELAY=-1");
    return dataSource;
  }
}
