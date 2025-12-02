package CET2041_P02.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "dept_emp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "employee")
@IdClass(DepartmentEmployeeId.class)
public class DepartmentEmployee {

  @Id
  @ManyToOne
  @JoinColumn(name = "emp_no",referencedColumnName = "emp_no",nullable = false)
  @JsonBackReference("emp-dept")
  private Employee employee;

  @Id
  @ManyToOne
  @JoinColumn(name = "dept_no",referencedColumnName = "dept_no",nullable = false)
  private Department department;

  @Column(name = "from_date",nullable = false)
  private LocalDate fromDate;

  @Column(name = "to_date",nullable = false)
  private LocalDate toDate;
}
