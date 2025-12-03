package CET2041_P02.controller;

import CET2041_P02.service.DepartmentService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * REST controller responsible for handling incoming HTTP requests related to
 * {@code Department} resources. This controller exposes endpoints under the
 * base path {@code /department} and delegates business logic to the
 * {@link DepartmentService}.
 *
 * <p>This class follows the typical REST API layered architecture:
 * <ul>
 *     <li><strong>Controller layer</strong>: receives HTTP requests and returns HTTP responses</li>
 *     <li><strong>Service layer</strong>: contains business logic</li>
 *     <li><strong>Repository layer</strong>: performs database operations</li>
 * </ul>
 *
 * <p>The controller produces JSON responses and integrates with JAX-RS running
 * on a servlet container such as Tomcat.
 */

@Path("/department")
public class DepartmentController {
    /**
     * Service instance used to perform business operations on Department data.
     * This field is initialized in the constructor and is used by the REST
     * endpoint methods to retrieve or manipulate data.
     */
    private DepartmentService departmentService;

    /**
     * Constructs a new {@code DepartmentController} and initializes its
     * underlying {@link DepartmentService}. This ensures that the controller
     * has all required dependencies to process incoming API calls.
     */
    public DepartmentController() {
        this.departmentService = new DepartmentService();
    }

    /**
     * Handles HTTP GET requests to retrieve all departments in the system.
     *
     * <p>API path: {@code GET /department}</p>
     *
     * <p>This method delegates the database read operation to the
     * {@link DepartmentService}, and returns a JSON array containing all
     * department records.</p>
     *
     * @return a JAX-RS {@link Response} containing:
     *     <ul>
     *         <li>HTTP 200 (OK) with a JSON list of departments, if successful</li>
     *         <li>HTTP 500 (Internal Server Error) if any unexpected exception occurs</li>
     *     </ul>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return departmentService.findAllDepartments();
    }
}
