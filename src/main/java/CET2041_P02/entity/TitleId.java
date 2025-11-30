package CET2041_P02.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class TitleId implements Serializable {
    @Column(name = "emp_no", nullable = false)
    private int empNo;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    public TitleId() {}

    public TitleId(int empNo, String title, LocalDate fromDate) {
        this.empNo = empNo;
        this.title = title;
        this.fromDate = fromDate;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleId titleId = (TitleId) o;
        return Objects.equals(empNo, titleId.getEmpNo()) && Objects.equals(fromDate,
                titleId.getFromDate()) && Objects.equals(title, titleId.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, title, fromDate);
    }
}
