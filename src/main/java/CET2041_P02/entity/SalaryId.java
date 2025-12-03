package CET2041_P02.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/** SalaryId Class to represent the composite primary key of the {@link Salary} entity */
@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class SalaryId  implements Serializable {

    /** Variable for the primary key for the {@link Employee} entity */
    @Column(name = "emp_no", nullable = false)
    private int empNo;

    /** Variable for start date of the salary */
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    /**
     * equals method to see if the SalaryId is the same
     * @param o Object to compare
     * @return true if the SalaryId is the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryId salaryId = (SalaryId) o;
        return Objects.equals(empNo, salaryId.empNo) && Objects.equals(fromDate, salaryId.fromDate);
    }
    /**
     *  Method to get the hash code
     * @return hashCode of the empNo and fromDate associated with the SalaryId
     */
    @Override
    public int hashCode() {
        return Objects.hash(empNo, fromDate);
    }

    /** No Argument Constructor for SalaryId */
    public SalaryId() {};
}
