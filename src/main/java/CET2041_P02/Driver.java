package CET2041_P02;

import CET2041_P02.dao.CompanyDAO;
import jakarta.ws.rs.core.Response;


public class Driver {

  public static void main(String[] args) {

    CompanyDAO companyDAO = new CompanyDAO();

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


  }

}
