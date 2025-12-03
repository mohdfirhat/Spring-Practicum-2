package CET2041_P02.config;

import CET2041_P02.EntityManager.AppEntityManagerFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Servlet context listener that manages application-level lifecycle events.
 * This listener allows the application to execute custom logic when the server
 * starts up and shuts down.
 *
 * <p>Its primary purpose in this application is to ensure that the shared
 * {@link AppEntityManagerFactory} is properly closed when the server stops
 * or when the application is redeployed, preventing resource leaks.</p>
 */
@WebListener
public class AppLifecycleListener implements ServletContextListener {

  /**
   * Called when the application is starting. Currently no startup logic
   * is required, but this method may be used to initialize global resources.
   *
   * @param sce the servlet context event for initialization
   */
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // optional: run code at startup
  }

  /**
   * Called when the application is shutting down. Ensures that the shared
   * {@code EntityManagerFactory} is closed to release database and JPA resources.
   *
   * @param sce the servlet context event for destruction
   */
  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    // This runs when the server stops or WAR redeploys
    AppEntityManagerFactory.getInstance().close();
  }
}
