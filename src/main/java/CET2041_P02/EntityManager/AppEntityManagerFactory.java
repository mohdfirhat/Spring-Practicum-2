package CET2041_P02.EntityManager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AppEntityManagerFactory {

  private static EntityManagerFactory emf;

  private AppEntityManagerFactory() {}

  public static EntityManagerFactory getInstance() {
    if (emf == null) {
      emf = Persistence.createEntityManagerFactory(
      "EmployeePersistence");
    }
    return emf;
  }

  public static void close() {
    if (emf != null && emf.isOpen()) {
      emf.close();
    }
  }

}
