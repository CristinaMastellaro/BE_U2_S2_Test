package cristinamastellaro.BE_U2_S2_Test.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeePayload(
        @NotBlank(message = "The username cannot be blank")
        @Size(min = 5, max = 30, message = "The username must have between 5 and 30 characters")
        String username,
        @NotBlank(message = "The name cannot be blank")
        @Size(min = 2, message = "The name must have at least two characters")
        String name,
        @NotBlank(message = "The surname cannot be blank")
        @Size(min = 2, message = "The surname must have at least two characters")
        String surname,
        @Email(message = "The string is not an email")
        @NotBlank(message = "The email cannot be blank")
        String email
) {
}
