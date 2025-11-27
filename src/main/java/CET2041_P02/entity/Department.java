package CET2041_P02.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
  @Id
  @Column(name = "dept_no", nullable = false, length = 4)
  private String deptNo;

  @Column(name = "dept_name", nullable = false, length = 40, unique = true)
  private String deptName;

  @OneToMany(mappedBy = "department")
  private List<DepartmentEmployee> employees;

  @OneToMany(mappedBy = "department")
  private List<DepartmentManager> managers;


  public Department() {}

  public Department(String deptNo, String deptName) {
    this.deptNo = deptNo;
    this.deptName = deptName;
  }


}
