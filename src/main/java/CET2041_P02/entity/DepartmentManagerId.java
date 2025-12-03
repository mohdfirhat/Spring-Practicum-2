package CET2041_P02.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/** DepartmentEmployeeId Class to represent the composite primary key of the {@link DepartmentManager} entity */
@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class DepartmentManagerId implements Serializable {

  /** Variable for the primary key for the {@link Employee} entity */
  private int manager;

  /** Variable for the primary key for the {@link Department} entity */
  private String department;

  /**
   * equals method to see if the DepartmentManagerId is the same
   * @param o Object to compare
   * @return true if the DepartmentManagerId is the same
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DepartmentManagerId)) return false;
    DepartmentManagerId that = (DepartmentManagerId) o;
    return manager == that.manager &&
      Objects.equals(department, that.department);
  }

  /**
   *  Method to get the hash code
   * @return hashCode of the Employee and Department entities associated with the DepartmentManagerId
   */
  @Override
  public int hashCode() {
    return Objects.hash(manager, department);
  }

  /** No Argument Constructor for DepartmentManagerId */
  public DepartmentManagerId() {};
}
