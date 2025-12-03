package CET2041_P02.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * Data transfer object used to capture promotion details submitted through
 * the employee promotion API. This object represents the required fields
 * provided in the POST request body.
 *
 * <p>All fields are simple values that describe the promotion change, such as
 * new department, new title, updated salary, and whether the employee becomes
 * a manager.</p>
 */
@Data
public class EmployeePromotionDto {

  /**
   * Department number to which the employee will be reassigned.
   */
  private String deptNo;

  /**
   * New title assigned to the employee after the promotion.
   */
  private String title;

  /**
   * Updated salary amount associated with the promotion.
   */
  private Integer salary;

  /**
   * Indicates whether the employee is promoted to a managerial role.
   */
  private Boolean isManager;

  /**
   * Date on which the promotion becomes effective.
   */
  private LocalDate effectiveDate;
}
