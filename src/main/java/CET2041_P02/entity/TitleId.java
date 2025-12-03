package CET2041_P02.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/** TitleId Class to represent the composite primary key of the {@link Title} entity */
@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class TitleId implements Serializable {

    /** Variable for the primary key for the {@link Employee} entity */
    @Column(name = "emp_no", nullable = false)
    private int empNo;

    /** Variable for name of title */
    @Column(name = "title", nullable = false)
    private String title;

    /** Variable for start date of the title */
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    /**
     * equals method to see if the TitleId is the same
     * @param o Object to compare
     * @return true if the TitleId is the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleId titleId = (TitleId) o;
        return Objects.equals(empNo, titleId.getEmpNo()) && Objects.equals(fromDate,
                titleId.getFromDate()) && Objects.equals(title, titleId.getTitle());
    }

    /**
     *  Method to get the hash code
     * @return hashCode of the empNo, title and fromDate associated with the TitleId
     */
    @Override
    public int hashCode() {
        return Objects.hash(empNo, title, fromDate);
    }

    /** No Argument Constructor for TitleId */
    public TitleId() {};
}
