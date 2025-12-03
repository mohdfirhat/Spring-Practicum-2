package CET2041_P02.service;

import CET2041_P02.EntityManager.AppEntityManagerFactory;
import CET2041_P02.dto.EmployeePromotionDto;
import CET2041_P02.dto.EmployeeRecordDto;
import CET2041_P02.dto.ErrorMessageDto;
import CET2041_P02.entity.DepartmentManager;
import CET2041_P02.entity.Employee;
import CET2041_P02.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Service layer for handling business operations related to {@link Employee}.
 * This class acts as a bridge between controller input and repository-level
 * database interactions, and is responsible for forming appropriate HTTP
 * responses.
 */
public class EmployeeService {
  /**
   * Repository used to perform database operations on employee data.
   */
  private EmployeeRepository employeeRepository;

  /**
   * Constructs the service and initializes its repository dependency.
   */
  public EmployeeService() {
    employeeRepository = new EmployeeRepository();
  }

  /**
   * Retrieves an employee by ID and returns an appropriate HTTP response.
   *
   * @param employeeId the employee identifier
   * @return HTTP 200 with employee data, or HTTP 404 if not found
   */
  public Response findEmployeeById(int employeeId) {
    Employee employee = employeeRepository.findEmployeeById(employeeId);
    if (employee == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(employee).build();
  }

  /**
   * Retrieves paginated employee records optionally filtered by department.
   *
   * @param deptNo department number filter (optional)
   * @param pageNo page number for pagination, must be >= 1
   * @return HTTP 200 with record list, or HTTP 400 for invalid input
   */
  public Response findEmployeeRecords(String deptNo, int pageNo) {
    if (pageNo < 1) {
      return Response.status(Response.Status.BAD_REQUEST).entity("Page Number must be more than 0").build();
    }
    pageNo--;

    List<EmployeeRecordDto> employeeRecords =employeeRepository.findEmployeeRecords(deptNo, pageNo);

    return Response.ok(employeeRecords).build();
  }

  /**
   * Applies a promotion to the specified employee using the provided
   * promotion details. This method performs validation and returns
   * an appropriate HTTP response.
   *
   * @param employeeId the employee to promote
   * @param employeePromotionDto the promotion details (new title, salary, etc.)
   * @return HTTP 200 on success, HTTP 404 if employee not found,
   *         or HTTP 400 with error message if validation or update fails
   */
  public Response promoteEmployee(int employeeId, EmployeePromotionDto employeePromotionDto) {
    // find employee
    Employee employee = employeeRepository.findEmployeeById(employeeId);

    // return not found response if employee not found
    if (employee == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }

    String errorMessage = employeeRepository.promoteEmployee(employeeId, employeePromotionDto);

    if (errorMessage != null) {
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }

    return Response.ok().build();
  }
}
