package CET2041_P02.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeePromotionDto {
  private String deptNo;
  private String title;
  private Integer salary;
  private Boolean isManager;
  private LocalDate effectiveDate;
}
