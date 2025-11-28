package CET2041_P02.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"departmentEmployees","departmentManagers"})
public class Employee {
  @Id
  @Column(name = "emp_no", nullable = false)
  private int empNo;

  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

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
  @JsonIgnore
  private List<DepartmentEmployee> departmentEmployees;

  @OneToMany(mappedBy = "manager")
  @JsonIgnore
  private List<DepartmentManager> departmentManagers;
}
