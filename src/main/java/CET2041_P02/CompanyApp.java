package CET2041_P02;

import CET2041_P02.config.JacksonConfig;
//import CET2041_P02.dao.CompanyDAO;
import CET2041_P02.controller.DepartmentController;
import CET2041_P02.controller.EmployeeController;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * JAX-RS application configuration class that defines the base API path
 * and registers all REST controllers and related components.
 *
 * <p>The {@code @ApplicationPath("/api")} annotation sets the root URL
 * for all exposed endpoints. All controllers registered in
 * {@link #getClasses()} will be accessible under this base path.</p>
 *
 * <p>This class replaces the need for a web.xml REST configuration when
 * running under a servlet container such as Tomcat.</p>
 */
@ApplicationPath("/api")
public class CompanyApp extends Application {
  /**
   * Registers REST controllers and supporting classes that make up
   * the application's JAX-RS resources.
   *
   * @return a set of resource classes to be managed by the JAX-RS runtime
   */
  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<>();
//    classes.add(CompanyDAO.class);
    classes.add(EmployeeController.class);
    classes.add(DepartmentController.class);
    classes.add(JacksonConfig.class);
    return classes;
  }
}
