package CET2041_P02.config;

import CET2041_P02.EntityManager.AppEntityManagerFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppLifecycleListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // optional: run code at startup
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    // This runs when the server stops or WAR redeploys
    AppEntityManagerFactory.getInstance().close();
  }
}
