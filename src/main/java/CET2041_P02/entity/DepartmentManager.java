package CET2041_P02.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "dept_manager")
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

  public Employee getManager() {
    return manager;
  }

  public void setManager(Employee manager) {
    this.manager = manager;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public void setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
  }

  public LocalDate getToDate() {
    return toDate;
  }

  public void setToDate(LocalDate toDate) {
    this.toDate = toDate;
  }
}
