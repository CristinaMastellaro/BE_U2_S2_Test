package cristinamastellaro.BE_U2_S2_Test.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("There is no resource with id " + id);
    }

    public NotFoundException(String email) {
        super("The email " + email + " does not exist");
    }
}
