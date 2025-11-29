package CET2041_P02.dto;

import lombok.*;


import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeRecordDto {
  private int empNo;
  private String firstName;
  private String lastName;
  private LocalDate hireDate;
}
