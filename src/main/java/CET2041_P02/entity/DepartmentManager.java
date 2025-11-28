package CET2041_P02.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "dept_manager")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@IdClass(DepartmentManagerId.class)
public class DepartmentManager {

  @Id
  @ManyToOne
  @JoinColumn(name = "emp_no",referencedColumnName = "emp_no",nullable = false)
  private Employee manager;

  @Id
  @ManyToOne
  @JoinColumn(name = "dept_no",referencedColumnName = "dept_no",nullable = false)
  private Department department;

  @Column(name = "from_date",nullable = false)
  private LocalDate fromDate;

  @Column(name = "to_date", nullable = false)
  private LocalDate toDate;
}
