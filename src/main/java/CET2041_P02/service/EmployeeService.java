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

public class EmployeeService {

  private EmployeeRepository employeeRepository;

  public EmployeeService() {
    employeeRepository = new EmployeeRepository();
  }

  public Response findEmployeeById(int employeeId) {
    Employee employee = employeeRepository.findEmployeeById(employeeId);
    if (employee == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(employee).build();
  }

  public Response findEmployeeRecords(String deptNo, int pageNo) {
    if (pageNo < 1) {
      return Response.status(Response.Status.BAD_REQUEST).entity("Page Number must be more than 0").build();
    }
    pageNo--;

    List<EmployeeRecordDto> employeeRecords =employeeRepository.findEmployeeRecords(deptNo, pageNo);

    return Response.ok(employeeRecords).build();
  }

  public Response promoteEmployee(int employeeId, EmployeePromotionDto employeePromotionDto) {
    // find employee
    Employee employee = employeeRepository.findEmployeeById(employeeId);

    // return not found response if employee not found
    if (employee == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }

    employeeRepository.promoteEmployee(employeeId, employeePromotionDto);

    return Response.ok().build();
  }
}
