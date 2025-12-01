package CET2041_P02.controller;

import CET2041_P02.EntityManager.AppEntityManagerFactory;
import CET2041_P02.dto.EmployeeRecordDto;
import CET2041_P02.dto.ErrorMessageDto;
import CET2041_P02.entity.Employee;
import CET2041_P02.service.EmployeeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController() {
    this.employeeService = new EmployeeService();
  }

  @GET
  @Path("/{employeeId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findEmployeeById(@PathParam("employeeId") int employeeId) {
    return employeeService.findEmployeeById(employeeId);
  }

  @GET
  @Path("/department/{deptNo}/page/{pageNo}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findEmployeeRecords(@PathParam("deptNo") String deptNo,
                                      @PathParam("pageNo") int pageNo) {
    return employeeService.findEmployeeRecords(deptNo, pageNo);
  }


}
