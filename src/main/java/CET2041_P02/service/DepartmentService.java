package CET2041_P02.service;

import CET2041_P02.entity.Department;
import CET2041_P02.repository.DepartmentRepository;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Service layer class that handles business logic related to
 * {@link Department} entities. Acts as an intermediary between
 * the controller layer and the repository layer.
 *
 * <p>Methods in this class typically retrieve data from the repository,
 * apply any required logic, and return standardized HTTP responses.</p>
 */
public class DepartmentService {
    /**
     * Repository used to perform database operations for departments.
     */
    private DepartmentRepository departmentRepository;

    /**
     * Constructs the service and initializes the underlying repository.
     */
    public DepartmentService() {
        this.departmentRepository = new DepartmentRepository();
    }

    /**
     * Retrieves all departments and returns them in an HTTP response.
     *
     * @return HTTP 200 response containing the list of all departments
     */
    public Response findAllDepartments() {
        List<Department> departments = departmentRepository.findAllDepts();
        return Response.ok(departments).build();
    }
}
