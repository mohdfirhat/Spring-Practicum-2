package CET2041_P02.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
  @Id
  @Column(name = "dept_no", nullable = false, length = 4)
  private String deptNo;

  @Column(name = "dept_name", nullable = false, length = 40, unique = true)
  private String deptName;

  @OneToMany(mappedBy = "department")
  @JsonIgnore
  private List<DepartmentEmployee> employees;

  @OneToMany(mappedBy = "department")
  @JsonIgnore
  private List<DepartmentManager> managers;
}
