package CET2041_P02.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "titles")
public class Title {
    @EmbeddedId
    private TitleId titleId;

    @Column(name = "to_date")
    private LocalDate toDate;

    @MapsId("empNo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", nullable = false)
    private Employee employee;

    public Title () {}

    public Title (TitleId titleId, LocalDate toDate, Employee employee) {
        this.titleId = titleId;
        this.toDate = toDate;
        this.employee = employee;
    }

    public TitleId getTitleId() {
        return titleId;
    }

    public void setTitleId(TitleId titleId) {
        this.titleId = titleId;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        if (employee != null) {
            if (this.titleId == null) {
                this.titleId = new TitleId();
            }
            this.titleId.setEmpNo(employee.getEmpNo());
        }
    }
}
