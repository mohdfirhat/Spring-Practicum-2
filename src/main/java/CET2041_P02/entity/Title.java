package CET2041_P02.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "titles")
public class Title {
    @Id
    private String empNo;
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Column
    private LocalDate fromDate;
    @Column
    private LocalDate toDate;
}
