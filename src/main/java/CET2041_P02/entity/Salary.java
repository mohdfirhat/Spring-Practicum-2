package CET2041_P02.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "salaries")
public class Salary {
    @EmbeddedId
    private SalaryId salaryId;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @MapsId("empNo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", nullable = false)
    private Employee employee;

    public Salary() {}

    public Salary(SalaryId sId, Employee employee, Integer salary, LocalDate toDate) {
        this.salaryId = sId;
        this.employee = employee;
        this.salary = salary;
        this.toDate = toDate;
    }

    public SalaryId getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(SalaryId salaryId) {
        this.salaryId = salaryId;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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
            if (this.salaryId == null) {
                this.salaryId = new SalaryId();
            }
            this.salaryId.setEmpNo(employee.getEmpNo());
        }
    }
}
