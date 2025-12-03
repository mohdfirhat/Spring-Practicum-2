package CET2041_P02.repository;

import CET2041_P02.EntityManager.AppEntityManagerFactory;
import CET2041_P02.dto.EmployeePromotionDto;
import CET2041_P02.dto.EmployeeRecordDto;
import CET2041_P02.entity.*;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.PathParam;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository class responsible for database operations related to {@link Employee}
 * entities. Uses JPA {@link EntityManager} created from the shared
 * {@link EntityManagerFactory}.
 */
public class EmployeeRepository {

  /**
   * Shared EntityManagerFactory used to create EntityManager instances.
   */
  private EntityManagerFactory emf;

  /**
   * Constructs the repository by obtaining the application's singleton
   * {@code EntityManagerFactory}.
   */
  public EmployeeRepository() {
    this.emf = AppEntityManagerFactory.getInstance().getEntityManagerFactory();
  }

  /**
   * Finds an employee by its identifier and initializes related collections
   * needed for further processing (departments, salaries, titles, managers).
   *
   * @param employeeId the employee identifier
   * @return the {@link Employee} instance, or {@code null} if not found
   */
  public Employee findEmployeeById(@PathParam("employeeId") int employeeId) {
    try (EntityManager em = emf.createEntityManager()){
      Employee employee = em.find(Employee.class,employeeId);
      employee.getDepartmentEmployees().size();
      employee.getDepartmentManagers().size();
      employee.getSalaries().size();
      employee.getTitles().size();

      return employee;
    }
  }

  /**
   * Retrieves a page of employee records filtered by department.
   * Uses a named query {@code Employee.findAllEmployeeRecords} and maps
   * results to {@link EmployeeRecordDto}.
   *
   * @param deptNo the department number to filter by
   * @param pageNo the page number for pagination
   * @return list of employee record DTOs for the given page
   */
  public List<EmployeeRecordDto> findEmployeeRecords( String deptNo,
                                       int pageNo) {
    int pageSize = 20;
    try (EntityManager em = emf.createEntityManager()){
      List<EmployeeRecordDto> employeeRecords =
        em.createNamedQuery("Employee.findAllEmployeeRecords", EmployeeRecordDto.class)
          .setParameter("deptNo",deptNo)
          .setFirstResult(pageNo*pageSize)
          .setMaxResults(pageSize)
          .getResultList();

      return employeeRecords;
    }
  }

  /**
   * Applies a promotion to an employee based on the given promotion details.
   * This method updates salary, title, department assignments, and manager
   * status within a single transaction.
   *
   * @param employeeId             the employee to be promoted
   * @param employeePromotionDto   the promotion details (new dept, title, salary, etc.)
   * @return {@code null} if the promotion is successful;
   *         an error message string if the employee is not found, the department
   *         does not exist, or an exception occurs
   */
  public String promoteEmployee(int employeeId, EmployeePromotionDto employeePromotionDto) {
    Employee employee = this.findEmployeeById(employeeId);

    if (employee == null) {
      return "Invalid employee Id: Employee not found";
    }


    // Getting manager status
    boolean isManager;
    List<DepartmentManager> managerList = employee.getDepartmentManagers();

    if (managerList.isEmpty()) {
      isManager = false;
    } else {
      if (LocalDate.now().isBefore(managerList.getLast().getToDate())) {
        isManager = true;
      } else {
        isManager = false;
      }
    }

    // Getting current DeptNo
    String currentDeptNo = employee.getDepartmentEmployees().getLast().getDepartment().getDeptNo();

    //Getting current Title
    Title oldTitle = employee.getTitles().getLast();


    try (EntityManager em = emf.createEntityManager()) {
      try {
        em.getTransaction().begin();

        //if same department
        if (currentDeptNo.equals(employeePromotionDto.getDeptNo())) {
          // Salary: update and create salary
          Salary oldSalary = employee.getSalaries().getLast();
          oldSalary.setToDate(employeePromotionDto.getEffectiveDate());
          em.merge(oldSalary);

          try {
            SalaryId salaryId = new SalaryId(employeeId,
              employeePromotionDto.getEffectiveDate());
            Salary newSalary = new Salary();
            newSalary.setSalaryId(salaryId);
            newSalary.setSalary(employeePromotionDto.getSalary());
            newSalary.setToDate(LocalDate.parse("9999-01-01"));
            newSalary.setEmployee(employee);
            em.persist(newSalary);
          } catch (EntityExistsException e) {
            throw new BadRequestException("The effective date conflicts with current salary adjustment date.");
          } catch (Exception e) {
            throw new BadRequestException(String.format("Create Salary fail : %s : %s",
              e.getClass().getName(),e.getMessage()));
          }


          // Title: if same do nothing
          // if diff,update and create title
          if (!oldTitle.getTitleId().getTitle().equals(employeePromotionDto.getTitle())) {
            oldTitle.setToDate(employeePromotionDto.getEffectiveDate());
            em.merge(oldTitle);

            try {
              TitleId newTitleId = new TitleId(employeeId,
                employeePromotionDto.getTitle(), employeePromotionDto.getEffectiveDate());
              Title newTitle = new Title();
              newTitle.setTitleId(newTitleId);
              newTitle.setToDate(LocalDate.parse("9999-01-01"));
              newTitle.setEmployee(employee);
              em.persist(newTitle);
            } catch (Exception e) {
              throw new BadRequestException(String.format("Create Title fail " +
                  ": %s : %s",
                e.getClass().getName(),e.getMessage()));
            }
          }
          // if newManager, update(if is currently manager) and create departmentmanager
          if (!isManager && employeePromotionDto.getIsManager()) {

            try {
              DepartmentManagerId newDepartmentManagerId =
                new DepartmentManagerId(employeeId, currentDeptNo);
              DepartmentManager newDepartmentManager = new DepartmentManager();
              newDepartmentManager.setManager(employee);
              newDepartmentManager.setDepartment(employee.getDepartmentEmployees().getLast().getDepartment());
              newDepartmentManager.setFromDate(employeePromotionDto.getEffectiveDate());
              newDepartmentManager.setToDate(LocalDate.parse("9999-01-01"));
              em.persist(newDepartmentManager);
            } catch (Exception e) {
              throw new BadRequestException(String.format("Create " +
                  "DepartmentManager fail : %s : %s",
                e.getClass().getName(),e.getMessage()));
            }
          }


          em.getTransaction().commit();
        } else {
          // Different Department
          // Salary: update and create salary
          Department newDepartment = em.find(Department.class, employeePromotionDto.getDeptNo());

          if (newDepartment == null) {
            throw new BadRequestException("Invalid Department Number: " +
              "Department not found");
          }


          //Update old salary
          Salary oldSalary = employee.getSalaries().getLast();
          oldSalary.setToDate(employeePromotionDto.getEffectiveDate());
          em.merge(oldSalary);

          try {
          //Create new salary
          SalaryId salaryId = new SalaryId(employeeId,
            employeePromotionDto.getEffectiveDate());
          Salary newSalary = new Salary();
          newSalary.setSalaryId(salaryId);
          newSalary.setSalary(employeePromotionDto.getSalary());
          newSalary.setToDate(LocalDate.parse("9999-01-01"));
          newSalary.setEmployee(employee);
          em.persist(newSalary);
          } catch (Exception e) {
            throw new BadRequestException(String.format("Create Salary fail : %s : %s",
              e.getClass().getName(),e.getMessage()));
          }


          // Title: if same do nothing
          // if diff,update and create title
          if (!oldTitle.getTitleId().getTitle().equals(employeePromotionDto.getTitle())) {
            // update oldTitle
            oldTitle.setToDate(employeePromotionDto.getEffectiveDate());
            em.merge(oldTitle);

            //create newTitle
            try {
              TitleId newTitleId = new TitleId(employeeId,
                employeePromotionDto.getTitle(), employeePromotionDto.getEffectiveDate());
              Title newTitle = new Title();
              newTitle.setTitleId(newTitleId);
              newTitle.setToDate(LocalDate.parse("9999-01-01"));
              newTitle.setEmployee(employee);
              em.persist(newTitle);
            } catch (Exception e) {
              throw new BadRequestException(String.format("Create Title fail " +
                  ": %s : %s",
                e.getClass().getName(),e.getMessage()));
            }
          }

          // DepartmentEmployee update and create
          // Update oldDepartmentEmployee
          DepartmentEmployee oldDeptEmp = employee.getDepartmentEmployees().getLast();
          oldDeptEmp.setToDate(employeePromotionDto.getEffectiveDate());
          em.merge(oldDeptEmp);

          // Create newDeptEmp
          try {
            DepartmentEmployee newDeptEmp = new DepartmentEmployee();
            newDeptEmp.setEmployee(employee);
            newDeptEmp.setDepartment(newDepartment);
            newDeptEmp.setToDate(LocalDate.parse("9999-01-01"));
            newDeptEmp.setFromDate(employeePromotionDto.getEffectiveDate());
            em.persist(newDeptEmp);
          } catch (Exception e) {
            throw new BadRequestException(String.format("Create " +
                "DepartmentEmployee fail : %s : %s",
              e.getClass().getName(),e.getMessage()));
          }


          // if newManager, update (if is currently manager) and create departmentmanager
          if (!isManager && employeePromotionDto.getIsManager()) {
            // update if employee was manager
            if (!employee.getDepartmentManagers().isEmpty()) {
              DepartmentManager oldDepartmentManager = employee.getDepartmentManagers().getLast();
              //only update if employee is currently manager (does not update old record of manager)
              if (oldDepartmentManager.getToDate().isAfter(employeePromotionDto.getEffectiveDate())) {
                oldDepartmentManager.setToDate(employeePromotionDto.getEffectiveDate());
                em.merge(oldDepartmentManager);
              }
            }

            // Create new DepartmentManager
            try {
              DepartmentManagerId newDepartmentManagerId =
                new DepartmentManagerId(employeeId, currentDeptNo);
              DepartmentManager newDepartmentManager = new DepartmentManager();
              newDepartmentManager.setManager(employee);
              newDepartmentManager.setDepartment(newDepartment);
              newDepartmentManager.setFromDate(employeePromotionDto.getEffectiveDate());
              newDepartmentManager.setToDate(LocalDate.parse("9999-01-01"));
              em.persist(newDepartmentManager);
            } catch (Exception e) {
              throw new BadRequestException(String.format("Create " +
                  "DepartmentManager fail : %s : %s",
                e.getClass().getName(),e.getMessage()));
            }
          }

          // if no longer manager, check and update
          if (!employeePromotionDto.getIsManager()) {
            if (!employee.getDepartmentManagers().isEmpty()) {
              DepartmentManager oldDepartmentManager = employee.getDepartmentManagers().getLast();
              if (oldDepartmentManager.getToDate().isAfter(employeePromotionDto.getEffectiveDate())) {
                oldDepartmentManager.setToDate(employeePromotionDto.getEffectiveDate());
                em.merge(oldDepartmentManager);
              }
            }
          }

          em.getTransaction().commit();
        }
      } catch (BadRequestException e) {
        em.getTransaction().rollback();
        em.close();
        return e.getMessage();
      } catch (Exception e) {
        em.getTransaction().rollback();
        em.close();
        return e.getMessage();
      }

      return null;
    }
  }

}