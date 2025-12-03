package CET2041_P02.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/** Department Class to represent the Department entity */
@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"employees","managers"})
@NamedQueries({
  @NamedQuery(name="Department.findAll",
              query="SELECT d FROM Department d")
  })
public class Department {

  /** Variable for Department number(primary key) */
  @Id
  @Column(name = "dept_no", nullable = false, length = 4)
  private String deptNo;

  /** Variable for Department name */
  @Column(name = "dept_name", nullable = false, length = 40, unique = true)
  private String deptName;

  /** Variable for history of employees who is/has worked in department */
  @OneToMany(mappedBy = "department")
  @JsonIgnore
  private List<DepartmentEmployee> employees;

  /** Variable for history of manager who is/has worked in department */
  @OneToMany(mappedBy = "department")
  @JsonIgnore
  private List<DepartmentManager> managers;

  /** No Argument Constructor for Department */
  public Department() {};
}
