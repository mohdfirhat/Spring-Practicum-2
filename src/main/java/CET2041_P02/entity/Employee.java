package CET2041_P02.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="employees")
public class Employee {
  @Id
  @Column(name = "emp_no", nullable = false)
  private int empNo;

  @Column(name = "birth_date", nullable = false)
  private LocalDate date;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender", nullable = false)
  private  Gender gender;

  @Column(name = "hire_date", nullable = false)
  private LocalDate hireDate;

  @OneToMany(mappedBy = "employee")
  private List<DepartmentEmployee> departmentEmployees;

  @OneToMany(mappedBy = "manager")
  private List<DepartmentManager> departmentManagers;



  public Employee() {}

  public Employee(int empNo, LocalDate date, Gender gender, LocalDate hireDate) {
    this.empNo = empNo;
    this.date = date;
    this.gender = gender;
    this.hireDate = hireDate;
  }

  public int getEmpNo() {
    return empNo;
  }

  public void setEmpNo(int empNo) {
    this.empNo = empNo;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public LocalDate getHireDate() {
    return hireDate;
  }

  public void setHireDate(LocalDate hireDate) {
    this.hireDate = hireDate;
  }

  public List<DepartmentEmployee> getDepartmentEmployees() {
    return departmentEmployees;
  }

  public void setDepartmentEmployees(List<DepartmentEmployee> departmentEmployees) {
    this.departmentEmployees = departmentEmployees;
  }

  public List<DepartmentManager> getDepartmentManagers() {
    return departmentManagers;
  }

  public void setDepartmentManagers(List<DepartmentManager> departmentManagers) {
    this.departmentManagers = departmentManagers;
  }

  @Override
  public String toString() {
    return "Employee{" +
      "empNo=" + empNo +
      ", date=" + date +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", gender=" + gender +
      ", hireDate=" + hireDate +
      ", departmentEmployees=" + departmentEmployees +
      ", departmentManagers=" + departmentManagers +
      '}';
  }
}
