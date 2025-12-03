package CET2041_P02.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/** DepartmentEmployeeId Class to represent the composite primary key of the {@link DepartmentEmployee} entity */
@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class DepartmentEmployeeId implements Serializable {

  /** Variable for the primary key for the {@link Employee} entity */
  private int employee;

  /** Variable for the primary key for the {@link Department} entity */
  private String department;

  /**
   * equals method to see if the DepartmentEmployeeId is the same
   * @param o Object to compare
   * @return true if the DepartmentEmployeeId is the same
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DepartmentEmployeeId that = (DepartmentEmployeeId) o;
    return employee == that.employee &&
      Objects.equals(department, that.department);
  }

  /**
   *  Method to get the hash code
   * @return hashCode of the Employee and Department associated with the DepartmentEmployeeId
   */
  @Override
  public int hashCode() {
    return Objects.hash(employee, department);
  }

  /** No Argument Constructor for DepartmentEmployeeId */
  public DepartmentEmployeeId() {};
}
