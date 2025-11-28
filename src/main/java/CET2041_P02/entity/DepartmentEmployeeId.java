package CET2041_P02.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEmployeeId implements Serializable {
  private int employee;

  private String department;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DepartmentEmployeeId that = (DepartmentEmployeeId) o;
    return employee == that.employee &&
      Objects.equals(department, that.department);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employee, department);
  }
}
