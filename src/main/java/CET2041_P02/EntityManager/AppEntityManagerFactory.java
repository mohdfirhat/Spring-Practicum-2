package CET2041_P02.EntityManager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AppEntityManagerFactory {

  private final EntityManagerFactory entityManagerFactory;

  private AppEntityManagerFactory() {
    entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePersistence");
  }

  private static class Singleton {
    private static final AppEntityManagerFactory INSTANCE = new AppEntityManagerFactory();
  }

  public static AppEntityManagerFactory getInstance() {
    return Singleton.INSTANCE;
  }

  public EntityManagerFactory getEntityManagerFactory() {
    return entityManagerFactory;
  }
  public void close() {
    if (entityManagerFactory.isOpen()) {
      entityManagerFactory.close();
    }
  }
}
