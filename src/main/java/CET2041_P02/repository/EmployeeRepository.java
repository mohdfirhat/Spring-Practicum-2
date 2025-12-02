package CET2041_P02.repository;

import CET2041_P02.EntityManager.AppEntityManagerFactory;
import CET2041_P02.dto.EmployeePromotionDto;
import CET2041_P02.dto.EmployeeRecordDto;
import CET2041_P02.dto.ErrorMessageDto;
import CET2041_P02.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

public class EmployeeRepository {

  private EntityManagerFactory emf;

  public EmployeeRepository() {
    this.emf = AppEntityManagerFactory.getInstance().getEntityManagerFactory();
  }

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

  public EmployeePromotionDto promoteEmployee(int employeeId, EmployeePromotionDto employeePromotionDto) {
    Employee employee = this.findEmployeeById(employeeId);

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
      em.getTransaction().begin();
      //if same department
      if (currentDeptNo.equals(employeePromotionDto.getDeptNo())) {
        // Salary: update and create salary
        Salary oldSalary = employee.getSalaries().getLast();
        oldSalary.setToDate(employeePromotionDto.getEffectiveDate());
        SalaryId salaryId = new SalaryId(employeeId,
          employeePromotionDto.getEffectiveDate());
//        Salary newSalary = new Salary(salaryId,
//          employeePromotionDto.getSalary(), LocalDate.parse("9999-01-01"),);
        Salary newSalary = new Salary();
        newSalary.setSalaryId(salaryId);
        newSalary.setSalary(employeePromotionDto.getSalary());
        newSalary.setToDate(LocalDate.parse("9999-01-01"));
        newSalary.setEmployee(employee);

        em.merge(oldSalary);
        em.persist(newSalary);


        // Title: if same do nothing
        // if diff,update and create title
        if (!oldTitle.getTitleId().getTitle().equals(employeePromotionDto.getTitle())) {
          oldTitle.setToDate(employeePromotionDto.getEffectiveDate());
          TitleId newTitleId = new TitleId(employeeId,
            employeePromotionDto.getTitle(),employeePromotionDto.getEffectiveDate());
          Title newTitle = new Title();
          newTitle.setTitleId(newTitleId);
          newTitle.setToDate(LocalDate.parse("9999-01-01"));
          newTitle.setEmployee(employee);

          em.merge(oldTitle);
          em.persist(newTitle);
        }
        // if newManager, update(if is currently manager) and create departmentmanager
        if (!isManager && employeePromotionDto.getIsManager()) {

          DepartmentManagerId newDepartmentManagerId =
            new DepartmentManagerId(employeeId,currentDeptNo);
          DepartmentManager newDepartmentManager = new DepartmentManager();
          newDepartmentManager.setManager(employee);
          newDepartmentManager.setDepartment(employee.getDepartmentEmployees().getLast().getDepartment());
          newDepartmentManager.setFromDate(employeePromotionDto.getEffectiveDate());
          newDepartmentManager.setToDate(LocalDate.parse("9999-01-01"));
          em.persist(newDepartmentManager);
        }


        em.getTransaction().commit();
      }

      return employeePromotionDto;
    }
  }

}