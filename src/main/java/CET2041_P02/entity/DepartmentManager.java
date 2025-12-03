package CET2041_P02.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/** DepartmentEmployee Class to represent the time period where an manager was working in a department */
@Entity
@Table(name = "dept_manager")
@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = "manager")
@IdClass(DepartmentManagerId.class)
public class DepartmentManager {

  /** Variable that represents the Employee(who is a manager) */
  @Id
  @ManyToOne
  @JoinColumn(name = "emp_no",referencedColumnName = "emp_no",nullable = false)
  @JsonBackReference("mng-dept")
  private Employee manager;

  /** Variable that represents the Department */
  @Id
  @ManyToOne
  @JoinColumn(name = "dept_no",referencedColumnName = "dept_no",nullable = false)
  private Department department;

  /** Variable for the start date of the manager working in the department */
  @Column(name = "from_date",nullable = false)
  private LocalDate fromDate;

  /** Variable for the end date of the manager working in the department */
  @Column(name = "to_date", nullable = false)
  private LocalDate toDate;

  /** No Argument Constructor for DepartmentManager */
  public DepartmentManager() {};
}
