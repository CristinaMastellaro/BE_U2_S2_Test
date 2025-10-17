package cristinamastellaro.BE_U2_S2_Test.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(nullable = false)
    private LocalDate dateOfRequest;
    private String preferencesOrNotes;

    @ManyToOne
    private Travel travel;
}
