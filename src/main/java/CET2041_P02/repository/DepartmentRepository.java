package CET2041_P02.repository;

import CET2041_P02.EntityManager.AppEntityManagerFactory;
import CET2041_P02.entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

/**
 * Repository class responsible for performing database operations
 * related to {@link Department} entities. This class interacts directly
 * with JPA's {@code EntityManager} to execute queries.
 *
 * <p>Each method creates a new {@code EntityManager} instance from the
 * shared {@link EntityManagerFactory}, ensuring thread safety and proper
 * resource handling.</p>
 */
public class DepartmentRepository {

    /**
     * The shared EntityManagerFactory used to create EntityManager instances.
     */
    private EntityManagerFactory emf;

    /**
     * Constructs the repository by retrieving the application's singleton
     * {@code EntityManagerFactory}.
     */
    public DepartmentRepository() {
        this.emf = AppEntityManagerFactory.getInstance().getEntityManagerFactory();
    }

    /**
     * Retrieves all departments from the database by executing the named query
     * {@code Department.findAll}.
     *
     * <p>A new {@code EntityManager} is created for the duration of the query
     * and automatically closed via the try-with-resources block.</p>
     *
     * @return a list of all {@link Department} entities
     */
    public List<Department> findAllDepts() {
        try (EntityManager em = emf.createEntityManager()) {
            List<Department> departments = em.createNamedQuery("Department.findAll", Department.class).getResultList();
            return departments;
        }
    }
}
