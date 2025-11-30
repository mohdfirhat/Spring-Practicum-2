package CET2041_P02.config;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages =
  {
    "CET2041_P02.config",
    "CET2041_P02.entity",
    "CET2041_P02.dao"
  })
public class AppConfig {

  @Bean
  public EntityManagerFactory entityManagerFactory() {
    return Persistence.createEntityManagerFactory("EmployeePersistence");
  }
}
