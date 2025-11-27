package CET2041_P02.dao;


import CET2041_P02.entity.Employee;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Set;

public class CompanyDAO {
  protected EntityManager em;

  public CompanyDAO(EntityManager em) { this.em = em; }

  public Employee findEmployee(int employeeId) {
    Employee employee = em.find(Employee.class, employeeId);

    if (employee != null) {
      employee.setDepartmentEmployees(new ArrayList<>());
      employee.setDepartmentEmployees(new ArrayList<>());
    }

    return employee;
  }

}
