package cristinamastellaro.BE_U2_S2_Test.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private LocalDate dateOfRequest;
    private String preferencesOrNotes;

    @ManyToOne
    private Travel travel;
}
