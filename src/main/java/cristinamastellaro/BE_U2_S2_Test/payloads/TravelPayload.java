package cristinamastellaro.BE_U2_S2_Test.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record TravelPayload(
        @NotBlank(message = "The destination must not be blank")
        @Size(min = 2, message = "The destination must have at least 2 characters")
        String destination,
        @NotNull(message = "The date must not be null")
        @Pattern(regexp = "^[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]$", message = "The date must be in the format yyyy-mm-dd")
        String date,
        @NotNull(message = "The employee's ID must not be null")
        UUID employeeId
) {
}
