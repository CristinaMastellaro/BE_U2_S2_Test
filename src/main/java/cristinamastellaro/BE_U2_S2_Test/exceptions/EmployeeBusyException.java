package cristinamastellaro.BE_U2_S2_Test.exceptions;

import java.time.LocalDate;
import java.util.UUID;

public class EmployeeBusyException extends RuntimeException {
    public EmployeeBusyException(String name, UUID id, LocalDate date) {
        super(name + " (id " + id + ") is already booked for the date " + date);
    }
}
