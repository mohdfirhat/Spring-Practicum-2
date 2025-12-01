package CET2041_P02.repository;

import CET2041_P02.EntityManager.AppEntityManagerFactory;
import CET2041_P02.dto.EmployeeRecordDto;
import CET2041_P02.dto.ErrorMessageDto;
import CET2041_P02.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class EmployeeRepository {

  private EntityManagerFactory emf;

  public EmployeeRepository() {
    this.emf = AppEntityManagerFactory.getInstance().getEntityManagerFactory();
  }

  public Employee findEmployeeById(@PathParam("employeeId") int employeeId) {
    try (EntityManager em = emf.createEntityManager()){
      Employee employee = em.find(Employee.class,employeeId);
      employee.getSalaries().size();
      employee.getTitles().size();

      return employee;
    }
  }

  public List<EmployeeRecordDto> findEmployeeRecords( String deptNo,
                                       int pageNo) {
    int pageSize = 20;
    try (EntityManager em = emf.createEntityManager()){
      List<EmployeeRecordDto> employeeRecords =
        em.createNamedQuery("Employee.findAllEmployeeRecords", EmployeeRecordDto.class)
          .setParameter("deptNo",deptNo)
          .setFirstResult(pageNo*pageSize)
          .setMaxResults(pageSize)
          .getResultList();

      return employeeRecords;
    }
  }
}