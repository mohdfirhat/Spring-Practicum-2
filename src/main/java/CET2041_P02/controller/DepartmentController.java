package CET2041_P02.controller;

import CET2041_P02.entity.Department;
import CET2041_P02.service.DepartmentService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController() {
        this.departmentService = new DepartmentService();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return departmentService.findAllDepartments();
    }
}
