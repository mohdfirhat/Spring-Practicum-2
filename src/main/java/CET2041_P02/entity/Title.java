package CET2041_P02.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/** Title Class to represent the Title entity */
@Entity
@Table(name = "titles")
@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = "employee")
public class Title {

    /** Variable for Title composite id(primary key) */
    @EmbeddedId
    private TitleId titleId;

    /** Variable for the start date of the employee salary */
    @Column(name = "to_date")
    private LocalDate toDate;

    /** Variable for the start date of the employee salary */
    @MapsId("empNo")
    @ManyToOne
    @JoinColumn(name = "emp_no", nullable = false)
    @JsonIgnore
    private Employee employee;

    /** No Argument Constructor for Title */
    public Title() {};
}
