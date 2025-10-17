package cristinamastellaro.BE_U2_S2_Test.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@ToString
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

    public Booking(LocalDate dateOfRequest, Travel travel) {
        this.dateOfRequest = dateOfRequest;
        this.travel = travel;
    }

    public Booking(LocalDate dateOfRequest, Travel travel, String preferencesOrNotes) {
        this(dateOfRequest, travel);
        this.preferencesOrNotes = preferencesOrNotes;
    }
}
