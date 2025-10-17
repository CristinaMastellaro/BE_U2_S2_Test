package cristinamastellaro.BE_U2_S2_Test.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "travels")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Travel {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StateTravel state;

    // Da come la traccia è scritta (e come spesso succede), penso che ogni viaggio sia associato a un unico dipendente
    @ManyToOne
    private Employee employee;
    @JsonIgnore
    @OneToMany
    private List<Booking> bookings;

    public Travel(String destination, LocalDate date, Employee employee) {
        this.destination = destination;
        this.date = date;
        this.employee = employee;
        state = StateTravel.PROGRAMMED;
    }

    public Travel(String destination, LocalDate date, Employee employee, StateTravel state) {
        this(destination, date, employee);
        this.state = state;
    }
}
