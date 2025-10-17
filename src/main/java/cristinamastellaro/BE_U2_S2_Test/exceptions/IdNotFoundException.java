package cristinamastellaro.BE_U2_S2_Test.exceptions;

import java.util.UUID;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(UUID id) {
        super("There is no resource with id " + id);
    }
}
