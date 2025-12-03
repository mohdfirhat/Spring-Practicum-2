package CET2041_P02.EntityManager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Singleton wrapper for creating and managing the application's
 * {@link EntityManagerFactory}. This ensures that only one instance of
 * the factory is created during the application's lifetime, which is
 * important because {@code EntityManagerFactory} is an expensive and
 * heavyweight resource.
 *
 * <p>The singleton is implemented using the "Initialization-on-demand
 * holder" pattern, which guarantees thread-safe lazy initialization
 * without requiring synchronization.</p>
 */
public class AppEntityManagerFactory {

  /**
   * The single {@code EntityManagerFactory} instance used by the application.
   */
  private final EntityManagerFactory entityManagerFactory;

  /**
   * Private constructor initializes the JPA {@code EntityManagerFactory}
   * using the persistence unit defined in {@code persistence.xml}.
   */
  private AppEntityManagerFactory() {
    entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePersistence");
  }

  /**
   * Inner static holder class that provides the lazy-loaded singleton instance.
   */
  private static class Singleton {
    private static final AppEntityManagerFactory INSTANCE = new AppEntityManagerFactory();
  }

  /**
   * Returns the singleton instance of the {@code AppEntityManagerFactory}.
   *
   * @return the shared singleton instance
   */
  public static AppEntityManagerFactory getInstance() {
    return Singleton.INSTANCE;
  }

  /**
   * Returns the underlying {@code EntityManagerFactory}.
   *
   * @return the JPA EntityManagerFactory used for creating EntityManager instances
   */
  public EntityManagerFactory getEntityManagerFactory() {
    return entityManagerFactory;
  }

  /**
   * Closes the {@code EntityManagerFactory} if it is still open.
   * This should be called when the application shuts down to release
   * database and JPA resources.
   */
  public void close() {
    if (entityManagerFactory.isOpen()) {
      entityManagerFactory.close();
    }
  }
}
