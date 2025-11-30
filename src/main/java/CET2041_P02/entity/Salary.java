package CET2041_P02.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "salaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "employee")
public class Salary {
    @EmbeddedId
    private SalaryId salaryId;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @MapsId("empNo")
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "emp_no", nullable = false)
    private Employee employee;
}
