package CET2041_P02.dto;

import lombok.*;


import java.time.LocalDate;
/**
 * Data transfer object representing basic employee record information.
 * This DTO is typically used when returning lightweight employee data
 * in list or summary views, without exposing full entity details.
 */

@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeRecordDto {

  /**
   * Unique identifier of the employee.
   */
  private int empNo;

  /**
   * Employee's given name.
   */
  private String firstName;

  /**
   * Employee's family name.
   */
  private String lastName;

  /**
   * Date when the employee was hired.
   */
  private LocalDate hireDate;

  /** No Argument Constructor for EmployeeRecordDto */
  public EmployeeRecordDto() {};
}
