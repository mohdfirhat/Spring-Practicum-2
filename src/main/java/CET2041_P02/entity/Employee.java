package CET2041_P02.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/** Employee Class to represent the Employee entity */
@Entity
@Table(name="employees")
@Getter
@Setter
@AllArgsConstructor
@ToString
@NamedQueries({
  @NamedQuery(name="Employee.findAllEmployeeRecords",
    query="SELECT NEW CET2041_P02.dto.EmployeeRecordDto(e.empNo,e.firstName,e.lastName,e" +
      ".hireDate) FROM Employee e " +
      "JOIN e.departmentEmployees de JOIN de.department d WHERE d.deptNo = :deptNo")
})
public class Employee {

  /** Variable for Employee number(primary key) */
  @Id
  @Column(name = "emp_no", nullable = false)
  private int empNo;

  /** Variable for birthdate of the employee  */
  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  /** Variable for first name of the employee  */
  @Column(name = "first_name", nullable = false)
  private String firstName;

  /** Variable for last name of the employee  */
  @Column(name = "last_name", nullable = false)
  private String lastName;

  /** Variable for gender of the employee  */
  @Enumerated(EnumType.STRING)
  @Column(name = "gender", nullable = false)
  private  Gender gender;

  /** Variable for hire date of the employee  */
  @Column(name = "hire_date", nullable = false)
  private LocalDate hireDate;

  /** Variable for history of department(s) the employee has worked in */
  @OneToMany(mappedBy = "employee")
  @JsonManagedReference("emp-dept")
  @OrderBy("toDate ASC")
  private List<DepartmentEmployee> departmentEmployees;

  /** Variable for history of department(s) the employee was a manager in */
  @OneToMany(mappedBy = "manager")
  @JsonManagedReference("mng-dept")
  @OrderBy("toDate ASC")
  private List<DepartmentManager> departmentManagers;

  /** Variable for history of salary earned by the employee */
  @OneToMany(mappedBy = "employee")
  @OrderBy("toDate ASC")
  private List<Salary> salaries;

  /** Variable for history of title for the employee */
  @OneToMany(mappedBy = "employee")
  @OrderBy("toDate ASC")
  private List<Title> titles;

  /** No Argument Constructor for Employee */
  public Employee() {};
}
