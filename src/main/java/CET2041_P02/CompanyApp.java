package CET2041_P02;

import CET2041_P02.config.JacksonConfig;
import CET2041_P02.dao.CompanyDAO;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class CompanyApp extends Application {
  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<>();
    classes.add(CompanyDAO.class);
    classes.add(JacksonConfig.class);
    return classes;
  }
}
