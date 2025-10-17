package cristinamastellaro.BE_U2_S2_Test.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record BookingPayload(
        @NotBlank(message = "The date of the request must not be null")
        @Pattern(regexp = "^[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]$", message = "The date must be in the format yyyy-mm-dd")
        String dateOfRequest,
        String notes,
        @NotNull(message = "You must indicate the UUID of the travel to book")
        UUID travelId
) {
}
