package CET2041_P02.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/** Salary Class to represent the Salary entity */
@Entity
@Table(name = "salaries")
@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = "employee")
public class Salary {

    /** Variable for the composite primary key  of the {@code Salary} entity*/
    @EmbeddedId
    private SalaryId salaryId;

    /** Variable for salary amount */
    @Column(name = "salary", nullable = false)
    private Integer salary;

    /** Variable for Department name */
    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    /** Variable for {@link Employee} reference to employee entity */
    @MapsId("empNo")
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "emp_no", nullable = false)
    private Employee employee;

    /** No Argument Constructor for Salary */
    public Salary() {};
}
