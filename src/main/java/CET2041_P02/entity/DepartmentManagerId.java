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
public class DepartmentManagerId implements Serializable {

  private int manager;
  private String department;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DepartmentManagerId)) return false;
    DepartmentManagerId that = (DepartmentManagerId) o;
    return manager == that.manager &&
      Objects.equals(department, that.department);
  }

  @Override
  public int hashCode() {
    return Objects.hash(manager, department);
  }
}
