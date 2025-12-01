package CET2041_P02.repository;

import CET2041_P02.EntityManager.AppEntityManagerFactory;
import CET2041_P02.entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class DepartmentRepository {

    private EntityManagerFactory emf;
    public DepartmentRepository() {
        this.emf = AppEntityManagerFactory.getInstance().getEntityManagerFactory();
    }

    public List<Department> findAllDepts() {
        try (EntityManager em = emf.createEntityManager()) {
            List<Department> departments = em.createNamedQuery("Department.findAll", Department.class).getResultList();
            return departments;
        }
    }
}
