package CET2041_P02.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "titles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "employee")
public class Title {
    @EmbeddedId
    private TitleId titleId;

    @Column(name = "to_date")
    private LocalDate toDate;

    @MapsId("empNo")
    @ManyToOne
    @JsonIgnore
//    @JsonBackReference
    @JoinColumn(name = "emp_no", nullable = false)
    private Employee employee;
}
