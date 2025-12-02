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
  @Path("")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findEmployeeRecords(@QueryParam("deptNo") String deptNo,
                                      @QueryParam("page") @DefaultValue("1") int pageNo) {
    return employeeService.findEmployeeRecords(deptNo, pageNo);
  }

  @POST
  @Path("/{employeeId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response promoteEmployee(@PathParam("employeeId") int employeeId, EmployeePromotionDto employeePromotionDto) {
    return employeeService.promoteEmployee(employeeId,employeePromotionDto);
  }


}
