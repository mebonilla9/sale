package dev.manuel.sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ArticulosApplication {

  public static void main(String[] args) {
    SpringApplication.run(ArticulosApplication.class, args);
  }

  @Bean
  public DataSource getDataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("org.postgresql.Driver");
    dataSourceBuilder.url("jdbc:postgresql://localhost:5432/sale");
    dataSourceBuilder.username("lord_nightmare");
    dataSourceBuilder.password("");
    return dataSourceBuilder.build();
  }

  @Bean
  public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
    return factory -> factory.setContextPath("/sale");
  }
}
