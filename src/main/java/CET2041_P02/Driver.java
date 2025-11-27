package CET2041_P02;

import CET2041_P02.dao.CompanyDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class Driver {
  static final String DBNAME = "employees";

  public static void main(String[] args) {
    // overriding the existing properties in persistence.xml
    Map<String,String> persistenceMap = new HashMap<>();
    persistenceMap.put("jakarta.persistence.jdbc.url",
      "jdbc:mariadb://localhost:3306/"+DBNAME);

    try (EntityManagerFactory emf = Persistence.createEntityManagerFactory(
      "EmployeePersistence", persistenceMap)) {
      EntityManager em = emf.createEntityManager();
      CompanyDAO companyDAO = new CompanyDAO(em);

      System.out.println(companyDAO.findEmployee(10001));


      em.close();
    }


  }

}
