package CET2041_P02.service;

import CET2041_P02.entity.Department;
import CET2041_P02.repository.DepartmentRepository;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public DepartmentService() {
        this.departmentRepository = new DepartmentRepository();
    }

    public Response findAllDepartments() {
        List<Department> departments = departmentRepository.findAllDepts();
        return Response.ok(departments).build();
    }
}
