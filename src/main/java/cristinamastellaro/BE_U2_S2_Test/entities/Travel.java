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
@AllArgsConstructor
@ToString
public class Travel {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private StateTravel state;

    // Da come la traccia è scritta (e come spesso succede), penso che ogni viaggio sia associato a un unico dipendente
    @ManyToOne
    private Employee employee;
    @JsonIgnore
    @OneToMany
    private List<Booking> bookings;
}
