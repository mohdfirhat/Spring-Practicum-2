package CET2041_P02.dao;


import CET2041_P02.EntityManager.AppEntityManagerFactory;
import CET2041_P02.entity.Department;
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


@Path("")
public class CompanyDAO {

  @GET
  @Path("/ping")
  public Response ping() {
    return Response.ok().entity("Service online").build();
  }

  @GET
  @Path("/department")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAllDepartment() {
    EntityManagerFactory emf = AppEntityManagerFactory.getInstance();

    try (EntityManager em = emf.createEntityManager()){
      List<Department> departments = em.createQuery("SELECT d FROM Department d",Department.class).getResultList();

      return Response.ok(departments).build();
    }
  }

  @GET
  @Path("/employee/{employeeId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findEmployeeById(@PathParam("employeeId") int employeeId) {
    EntityManagerFactory emf = AppEntityManagerFactory.getInstance();

    try (EntityManager em = emf.createEntityManager()){
      Employee employee = em.find(Employee.class,employeeId);

      return Response.ok(employee).build();
    }
  }

}
