package CET2041_P02;

import CET2041_P02.config.AppConfig;
import CET2041_P02.dao.CompanyDAO;
import jakarta.ws.rs.core.Response;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Driver {

  public static void main(String[] args) {

    try (var context =
           new AnnotationConfigApplicationContext(AppConfig.class)) {
      CompanyDAO companyDAO = context.getBean(CompanyDAO.class);

      try (Response res = companyDAO.findAllDepartment()) {
        System.out.println("\n\n---------- GET All Departments ----------");
        System.out.println("📤Response Object:");
        System.out.println(res.toString());
        System.out.println(res.getEntity().toString());
        System.out.println("\n\n");
      }

      try (Response res = companyDAO.findEmployeeById(10001)) {
        System.out.println("\n\n---------- GET Employee By Id ----------");
        System.out.println("📤Response Object:");
        System.out.println(res.toString());
        System.out.println(res.getEntity().toString());
        System.out.println("\n\n");
      }

      try (Response res = companyDAO.findEmployeeRecords("d001",1)) {
        System.out.println("\n\n---------- GET Employee Record Department " +
          "'d001' and page 1 ----------");
        System.out.println("📤Response Object:");
        System.out.println(res.toString());
        System.out.println(res.getEntity().toString());
        System.out.println("\n\n");
      }

      try (Response res = companyDAO.findEmployeeRecords("d001",0)) {
        System.out.println("\n\n---------- GET Employee Record Department " +
          "'d001' and page 0 (ErrorMessage) ----------");
        System.out.println("📤Response Object:");
        System.out.println(res.toString());
        System.out.println(res.getEntity().toString());
        System.out.println("\n\n");
      }

    }


  }

}
