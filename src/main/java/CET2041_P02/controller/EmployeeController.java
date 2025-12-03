package CET2041_P02.controller;

import CET2041_P02.EntityManager.AppEntityManagerFactory;
import CET2041_P02.dto.EmployeePromotionDto;
import CET2041_P02.dto.EmployeeRecordDto;
import CET2041_P02.dto.ErrorMessageDto;
import CET2041_P02.entity.Employee;
import CET2041_P02.service.EmployeeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PostLoad;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * REST controller for handling employee-related requests.
 * Provides endpoints to fetch a single employee or query employee records.
 */
@Path("/employee")
public class EmployeeController {

  /**
   * Service layer used to perform employee operations.
   */
  private final EmployeeService employeeService;

  /**
   * Initializes the controller and its service dependency.
   */
  public EmployeeController() {
    this.employeeService = new EmployeeService();
  }

  /**
   * Retrieves a specific employee by ID.
   *
   * @param employeeId the employee's identifier
   * @return HTTP 200 with employee data, or appropriate error response
   */
  @GET
  @Path("/{employeeId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findEmployeeById(@PathParam("employeeId") int employeeId) {
    return employeeService.findEmployeeById(employeeId);
  }

  /**
   * Retrieves employee records by optional department filter and page number.
   *
   * @param deptNo department number to filter (optional)
   * @param pageNo page number for paginated results (default is 1)
   * @return HTTP 200 with result list, or appropriate error response
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response findEmployeeRecords(@QueryParam("deptNo") String deptNo,
                                      @QueryParam("page") @DefaultValue("1") int pageNo) {
    return employeeService.findEmployeeRecords(deptNo, pageNo);
  }

  /**
   * Promotes an employee by applying the provided promotion details.
   *
   * @param employeeId the ID of the employee to promote
   * @param employeePromotionDto promotion data such as new title or salary
   * @return HTTP 200 with updated employee info, or appropriate error response
   */
  @POST
  @Path("/{employeeId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response promoteEmployee(@PathParam("employeeId") int employeeId, EmployeePromotionDto employeePromotionDto) {
    return employeeService.promoteEmployee(employeeId,employeePromotionDto);
  }


}
