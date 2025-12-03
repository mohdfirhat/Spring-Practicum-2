package CET2041_P02.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/** DepartmentEmployee Class to represent the time period where an employee was working in a department */
@Entity
@Table(name = "dept_emp")
@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = "employee")
@IdClass(DepartmentEmployeeId.class)
public class DepartmentEmployee {

  /** Variable that represents the Employee */
  @Id
  @ManyToOne
  @JoinColumn(name = "emp_no",referencedColumnName = "emp_no",nullable = false)
  @JsonBackReference("emp-dept")
  private Employee employee;

  /** Variable that represents the Department */
  @Id
  @ManyToOne
  @JoinColumn(name = "dept_no",referencedColumnName = "dept_no",nullable = false)
  private Department department;

  /** Variable for the start date of the employee working in the department */
  @Column(name = "from_date",nullable = false)
  private LocalDate fromDate;

  /** Variable for the end date of the employee working in the department */
  @Column(name = "to_date",nullable = false)
  private LocalDate toDate;

  /** No Argument Constructor for DepartmentEmployee */
  public DepartmentEmployee() {};
}
